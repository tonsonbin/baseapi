package com.tyfo.app.model.sys.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tyfo.app.common.YamlConfig;
import com.tyfo.app.common.exception.ServiceException;
import com.tyfo.app.common.security.IdGen;
import com.tyfo.app.common.utils.aliOSS.ALYOssFileUpload;
import com.tyfo.app.common.utils.httpSend.FileUtils;
import com.tyfo.app.common.utils.judgeFileType.FileTypeJudgeByName;
import com.tyfo.app.common.web.Result;
import com.tyfo.app.common.web.ResultCode;
import com.tyfo.app.common.web.ResultGenerator;


/**
* Created by CodeGenerator on 2020/06/10.
* 文件
*/
@RestController
@RequestMapping("${apiPath}/file")
public class AppFileController {

	/**
	 * 异步上传文件
	 * @param file
	 * @param uploadFileType 1 表示图片,2 表示文档,3 表示视频,4 表示种子,5 表示音乐,7 表示其它
	 * @param cpath 中间路径
	 * @param cpath isALY 是否是上传到阿里云服务器
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="ajax")
	public Result ajax(@RequestParam("files")MultipartFile[] files,@RequestParam("uploadFileType")String uploadFileType,String cpath,boolean isALY) {
		
		if(files == null || files.length <=0 ) {
			
			throw new ServiceException("不能上传空文件");
			
		}
		if(StringUtils.isNoneBlank(cpath) && (cpath.contains("./") || cpath.length() > 100)) {

			throw new ServiceException("自定义路径不合法！");
			
		}else if(StringUtils.isBlank(cpath)) {
			
			cpath = "/";
			
		}
		if(StringUtils.isBlank(uploadFileType)) {

			throw new ServiceException("参数错误，文件类型不能为空！");
			
		}
		List<Map<String,Object>> pathInfos = Lists.newArrayList();
		try {
			for(MultipartFile file : files) {
				
				boolean isOk = true;
				String fileBasePath = YamlConfig.getUserFilesBaseDir()+YamlConfig.getUserFilesBaseUrl()+"/"+cpath+"/";
				String fileBaseUrl = YamlConfig.getUserFilesBaseUrl()+"/"+cpath+"/";
				String fileName = file.getOriginalFilename();
				String newFileName = IdGen.uuid()+"."+FileUtils.getFileExtension(fileName);
				String url = "";

				//判断文件格式
				Integer fileType = FileTypeJudgeByName.getType(fileName);
				if(!uploadFileType.contains(String.valueOf(fileType))) {
					throw new ServiceException("请上传正式格式文件！");
				};
				String filePath = fileBasePath+"/"+newFileName;
				File file2 = new File(filePath);
				if(!file2.getParentFile().exists()) {
					file2.getParentFile().mkdirs();
				}
				if(file == null || file.getSize() <= 0) {
					isOk = false;
				}else {
					
					file.transferTo(file2);
					
					if(isALY) {//存至阿里云服务器
						
						Result toAlyRes = toALY(cpath, file2);
						if(ResultCode.SUCCESS.code() != toAlyRes.getCode()) {//上传失败
							isOk = false;
						}else url = (String) toAlyRes.getData();
						
					}else url = fileBaseUrl+"/"+newFileName;
					
				}
				
				
				
				Map<String,Object> path = Maps.newHashMap();
				path.put("oldName", fileName);
				path.put("newName", newFileName);
				path.put("url", url);//如果是阿里云则是阿里云的data
				path.put("fileType",fileType);
				path.put("isOk",isOk);//是否处理成功
				
				pathInfos.add(path);
			}
			
			return ResultGenerator.genSuccessResult(pathInfos);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			throw new ServiceException("无法保存文件或文件损坏");
		}
	}
	
	/**
	 * 将文件上传至阿里云
	 * @param cpath
	 * @param file
	 * @return
	 */
	private Result toALY(String cpath,File file) {
		
		String data = "files/"+cpath+"/"+file.getName();
		
		if(!ALYOssFileUpload.upload(data, file)) {

			return new Result().setCode(ResultCode.FAIL).setMessage("文件处理失败-aly");
			
		};
		
		return ResultGenerator.genSuccessResult();
	}
	
}
