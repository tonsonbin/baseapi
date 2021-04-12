package com.tb.app.model.weixin.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.app.common.YamlConfigWeixin;
import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.security.IdGen;
import com.tb.app.common.service.CrudService;
import com.tb.app.model.sys.entity.User;
import com.tb.app.model.sys.service.SmsService;
import com.tb.app.model.sys.utils.tokenUtils.TokenUtils;
import com.tb.app.model.weixin.common.WXConstant;
import com.tb.app.model.weixin.entity.WXBindMobile;
import com.tb.app.model.weixin.mapper.WXBindMobileMapper;


/**
 * Created by CodeGenerator on 2020/02/08.
 */
@Service
@Transactional(readOnly = true)
public class WXBindMobileService extends CrudService<WXBindMobileMapper, WXBindMobile> {

    @Resource
    private WXBindMobileMapper userBindTelMapper;

    @Resource
    private SmsService smsService;

    @Resource
    private WXBaseInfoService wxBaseInfoService;

    /**
     * 将该openId对应的非改userId的数据设置为非默认
     *
     * @param userBindTelSave
     */
    @Transactional(readOnly = false)
    public void unDefault(WXBindMobile wxBindMobile) {

        if (wxBindMobile == null) {

            throw new ServiceException("userBindTelSave 不能为空！");

        }

        if (StringUtils.isBlank(wxBindMobile.getOpenId()) || StringUtils.isBlank(wxBindMobile.getId())) {

            throw new ServiceException("openId和id不能为空！");

        }

        wxBindMobile.setAppId(YamlConfigWeixin.getAppId());
        
        dao.unDefault(wxBindMobile);

    }

    /**
     * 获取该openId默认的绑定信息
     *
     * @param openId
     * @return
     */
    public WXBindMobile findDefaultTelUser(String openId,String appId) {

        return dao.findDefaultTelUser(openId,appId);

    }

    /**
     * 设置默认
     *
     * @param loginPone 登录的手机号
     * @param phone     要设置默认的手机号
     * @return token
     */
    @Transactional(readOnly = false)
    public String setDefault(String loginPone, String phone) {

        //校验参数
        if (StringUtils.isBlank(phone)) {
            throw new ServiceException("phone（手机号不能为空）！");
        }

        //根据手机号取openId
        WXBindMobile wxBindMobile = getWXBindMobileByPhone(loginPone);

        //将该用户设置为默认
        wxBindMobile.setAppId(YamlConfigWeixin.getAppId());
        //数据入库
        dao.defaultById(wxBindMobile);
        
        //将openId非该数据的数据设置为非默认
        unDefault(wxBindMobile);

        //根据新的手机号取token
        User user = new User();
        user.setLoginName(phone);
        String token = TokenUtils.getToken(user);

        return token;

    }

    /**
     * 添加绑定手机号
     * <p>
     * 1、校验验证码
     * 2、绑定手机号返回该手机号的token
     *
     * @param loginPhone 当前登录用户的手机号
     * @param phone      要绑定的手机号
     * @param verifyCode 手机验证码
     * @param beDefault  是否设置为默认 0 默认 1非默认
     * @return token
     */
    @Transactional(readOnly = false)
    public String addBindPhone(String loginPhone, String phone, String verifyCode, String beDefault) {

        //校验参数
        if (StringUtils.isBlank(phone)) {

            throw new ServiceException("phone（要绑定的手机号）不能为空！");

        }
        if (StringUtils.isBlank(verifyCode)) {

            throw new ServiceException("verifyCode（验证码）不能为空！");

        }
        //如果是否默认为空，这设置为非默认
        if (StringUtils.isBlank(beDefault)) {

            beDefault = WXConstant.USER_BIND_ISDEFAULT_NO;

        }

        //校验手机验证码是否正确
        smsService.verificationCode(phone, verifyCode, "0");
        

        //验证码校验成功，进行手机号绑定操作
        String openId = getWXBindMobileByPhone(loginPhone).getOpenId();

        //绑定手机号
        bindHelp(phone, openId, beDefault);

        //返回该手机号对应的token
        User user = new User();
        user.setLoginName(phone);
        String token = TokenUtils.getToken(user);

        return token;

    }

    /**
     * 解除绑定
     * <p>
     * 1、不能解除当前登录的手机号
     *
     * @param loginPhone
     * @param phone
     */
    @Transactional(readOnly = false)
    public void unBind(String loginPhone, String phone) {

        //判断是否为登录的手机号
        if (StringUtils.equals(loginPhone, phone)) {

            //是当前登录的手机号
            throw new ServiceException("不可解除当前登录手机号！");

        }

        //根据loginPone取绑定信息
        WXBindMobile wxBindMobile = getWXBindMobileByPhone(phone);

        //移除绑定信息
        dao.delete(wxBindMobile);

    }

    /**
     * 查找当前登录用户已经绑定的手机号
     *
     * @param loginPhone
     * @return
     */
    public List<WXBindMobile> findListByPhone(String loginPhone) {

        //根据loginPhone取绑定信息
        WXBindMobile userBindTel = getWXBindMobileByPhone(loginPhone);

        //根据openId取对应的绑定的信息列表
        WXBindMobile userBindTelQ = new WXBindMobile();
        userBindTelQ.setOpenId(userBindTel.getOpenId());
        userBindTelQ.setAppId(YamlConfigWeixin.getAppId());
        List<WXBindMobile> userBindTels = findList(userBindTelQ);

        return userBindTels;
    }

    /**
     * 绑定逻辑处理-设置默认
     *
     * @param phone  要绑定的手机号
     * @param openId 要绑定到的openId
     */
    @Transactional(readOnly = false)
    void bindHelp(String phone, String openId) {

        bindHelp(phone, openId, WXConstant.USER_BIND_ISDEFAULT_YES);

    }

    /**
     * 绑定逻辑处理
     * <p>
     * 一个手机号只能绑定到一个openId上，不然可能会导致多个微信同时使用一个手机号的情况
     * 当时一个openId可以绑定多个手机号
     * <p>
     * 1、会判断是否存在对应手机号绑定信息
     * 1.1、不存在则添加为新的绑定数据
     * 1.2、存在判断绑定的手机号是不是该openId
     * 1.1.1、如果不是则解除之前的绑定，然后绑定传入的手机号
     * 1.2.1、如果是则将该手机号设置为默认手机号
     *
     * @param phone     要绑定的手机号
     * @param openId    要绑定到的openId
     * @param beDefault 新加判断是否绑定的时候设置为默认
     */
    @Transactional(readOnly = false)
    void bindHelp(String phone, String openId, String beDefault) {

        //判断是否存在
        WXBindMobile wxBindMobile = getWXBindMobileByPhone(phone);
        if (wxBindMobile != null) {

            //已经存在了该手机号了，并且当前用户为该手机号用户，则将该手机号设置为默认手机号
            //判断该手机号当前绑定的openId是不是当前微信的openId，如果不是，则将该手机号与之前的openId进行解绑
            if (!StringUtils.equals(wxBindMobile.getOpenId(), openId)) {

                //该手机号已经绑定过其他的openId，进行解绑，直接移除这一条绑定数据即可
                delete(wxBindMobile);

            } else {

                //该手机号绑定的是当前的openId，则只是更新
                wxBindMobile.setIsNewRecord(false);

            }

        } else {

            //不存在该手机号，则加入该手机号
            wxBindMobile = new WXBindMobile();
            wxBindMobile.setId(IdGen.uuid());
            wxBindMobile.setIsNewRecord(true);
            wxBindMobile.setCreateDate(new Date());

        }
        //将该用户设置为默认
        wxBindMobile.setIsDefault(beDefault);
        wxBindMobile.setMobile(phone);
        wxBindMobile.setDelFlag("0");
        wxBindMobile.setOpenId(openId);
        wxBindMobile.setAppId(YamlConfigWeixin.getAppId());
        //数据入库
        save(wxBindMobile);

        if (StringUtils.equals(WXConstant.USER_BIND_ISDEFAULT_YES, beDefault)) {

            //设置了默认，将该openId对应的除了该用户id的其他数据设置为非默认
            unDefault(wxBindMobile);

        }

    }

    /**
     * 根据phone取绑定信息
     * <p>
     * 理论上一个手机号只会有一条数据，这里如果该手机号取到了多个绑定信息则会直接抛错
     * <p>
     * 1、如果没有取到则返回null
     * 2、如果传入的phone是当前登录的phone，则肯定能取到
     *
     * @param phone
     * @return
     */
    public WXBindMobile getWXBindMobileByPhone(String phone) {

        //根据登录手机号取openid
        WXBindMobile wxBindMobileQ = new WXBindMobile();
        wxBindMobileQ.setMobile(phone);
        wxBindMobileQ.setAppId(YamlConfigWeixin.getAppId());
        List<WXBindMobile> wxBindMobiles = dao.findList(wxBindMobileQ);

        //判断是否有取到信息
        if (wxBindMobiles == null || wxBindMobiles.isEmpty()) {

            //没有取到对应信息
            return null;

        }

        //理论上一个手机号只会有一条数据
        if (wxBindMobiles.size() > 1) {
            throw new ServiceException("数据异常，该手机号绑定了多个微信账号！");
        }

        //根据当前登录的手机号取得的绑定的openId肯定是有数据的
        return wxBindMobiles.get(0);
    }

}
