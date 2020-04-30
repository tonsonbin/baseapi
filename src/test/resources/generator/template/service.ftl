package ${basePackage}.service;

import CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${basePackage}.model.${modelNameUpperCamel};
import com.coolsn.app.mapper.${modelNameUpperCamel}Mapper;
import Page;
import java.util.List;



/**
 * Created by ${author} on ${date}.
 */
@Service
@Transactional(readOnly = true)
public class ${modelNameUpperCamel}Service extends CrudService<${modelNameUpperCamel}Mapper,${modelNameUpperCamel}> {

    public ${modelNameUpperCamel} get(String id) {
		return super.get(id);
	}

	public List<${modelNameUpperCamel}> findList(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        return super.findList(${modelNameLowerCamel});
    }

    public Page<${modelNameUpperCamel}> findPage(Page<${modelNameUpperCamel}> page, ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        return super.findPage(page, ${modelNameLowerCamel});
    }

    @Transactional(readOnly = false)
    public void save(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        super.save(${modelNameLowerCamel});
    }

    @Transactional(readOnly = false)
    public void delete(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        super.delete(${modelNameLowerCamel});
    }
}
