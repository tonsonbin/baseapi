import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * @Description mybatis代码生成模板
 * @Author Benjamin
 * @CreateDate 2018-12-19 15:06
 **/
public class GeneratorPlugin extends PluginAdapter {

    //不需要生成的字段
    private String fields = "id,remarks,createby,createdate,updateby,updatedate,delflag";


    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }


    /**
     * 生成Mapper
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType imp = new FullyQualifiedJavaType("CrudMapper");
        interfaze.addImportedType(imp);// 添加import CrudMapper;
        interfaze.getMethods().clear();
        return true;
    }

    /**
     * 生成实体
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType imp = new FullyQualifiedJavaType("DataEntity");
        topLevelClass.addImportedType(imp);// 添加import DataEntity;
        return true;
    }


    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String ref = introspectedTable.getBaseRecordType();
        //model名称
        String modelName = ref.substring(ref.lastIndexOf(".") + 1);
        //表名称
        String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
        //
        int columnsSize = introspectedTable.getAllColumns().size();
        //通用From 语句
        TextElement fromEle = new TextElement(" FROM " + tableName + " a");


        //columns--通用查询列
        XmlElement columnsSql = new XmlElement("sql");
        columnsSql.addAttribute(new Attribute("id", "columns"));

        //a_b AS aB
        introspectedTable.getAllColumns().forEach(introspectedColumn -> {
            String value = "    a." + introspectedColumn.getActualColumnName();
            value += " AS ";
            value += "\"" + introspectedColumn.getJavaProperty() + "\"";
            if (introspectedTable.getAllColumns().indexOf(introspectedColumn) != columnsSize - 1) {
                value += ",";
            }
            columnsSql.addElement(new TextElement(value));
        });

        //columns 的include 节点
        XmlElement includeColumns = new XmlElement("include");
        includeColumns.addAttribute(new Attribute("refid", "columns"));

        //joins
        XmlElement joinsSql = new XmlElement("sql");
        joinsSql.addAttribute(new Attribute("id", "joins"));
        joinsSql.addElement(new TextElement(""));

        //joins 的include 节点
        XmlElement includeJoins = new XmlElement("include");
        includeJoins.addAttribute(new Attribute("refid", "joins"));


        //get
        XmlElement getSql = new XmlElement("select");
        getSql.addAttribute(new Attribute("id", "get"));
        getSql.addAttribute(new Attribute("resultType", modelName));
        getSql.addElement(new TextElement("SELECT"));
        getSql.addElement(includeColumns);
        getSql.addElement(fromEle);
        getSql.addElement(includeJoins);
        getSql.addElement(new TextElement(" WHERE a.id = #{id}"));


        //where--通用的where语句
        XmlElement where = new XmlElement("where");
        where.addElement(new TextElement("a.del_flag = #{DEL_FLAG_NORMAL}"));

        //choose--通用的choose语句
        XmlElement choose = new XmlElement("choose");
        //构建choose的when语句
        XmlElement when = new XmlElement("when");
        when.addAttribute(new Attribute("test", "page !=null and page.orderBy != null and page.orderBy != ''"));
        when.addElement(new TextElement("ORDER BY ${page.orderBy}"));
        choose.addElement(when);
        //构建choose的otherwise语句
        XmlElement otherwise = new XmlElement("otherwise");
        otherwise.addElement(new TextElement("ORDER BY a.update_date DESC"));
        choose.addElement(otherwise);

        //findList
        XmlElement findListSql = new XmlElement("select");
        findListSql.addAttribute(new Attribute("id", "findList"));
        findListSql.addAttribute(new Attribute("resultType", modelName));
        findListSql.addElement(new TextElement("SELECT"));
        findListSql.addElement(includeColumns);
        findListSql.addElement(fromEle);
        findListSql.addElement(includeJoins);
        findListSql.addElement(where);
        findListSql.addElement(choose);

        //findAllList
        XmlElement findAllListSql = new XmlElement("select");
        findAllListSql.addAttribute(new Attribute("id", "findAllList"));
        findAllListSql.addAttribute(new Attribute("resultType", modelName));
        findAllListSql.addElement(new TextElement("SELECT"));
        findAllListSql.addElement(includeColumns);
        findAllListSql.addElement(fromEle);
        findAllListSql.addElement(includeJoins);
        findAllListSql.addElement(where);
        findAllListSql.addElement(choose);

        //insert
        XmlElement insertSql = new XmlElement("insert");
        insertSql.addAttribute(new Attribute("id", "insert"));
        insertSql.addElement(new TextElement("  INSERT INTO " + tableName + "("));
        //key
        introspectedTable.getAllColumns().forEach(introspectedColumn -> {
            String name = " " + introspectedColumn.getActualColumnName();
            if (introspectedTable.getAllColumns().indexOf(introspectedColumn) != columnsSize - 1) {
                name += ",";
            }
            insertSql.addElement(new TextElement(name));
        });
        insertSql.addElement(new TextElement(") VALUES ("));
        //value
        introspectedTable.getAllColumns().forEach(introspectedColumn -> {
            String name = " #{" + introspectedColumn.getJavaProperty() + "}";

            if (introspectedTable.getAllColumns().indexOf(introspectedColumn) != columnsSize - 1) {
                name += ",";
            }
            insertSql.addElement(new TextElement(name));
        });
        insertSql.addElement(new TextElement(")"));

        //update
        XmlElement updateSql = new XmlElement("update");
        updateSql.addAttribute(new Attribute("id", "update"));
        updateSql.addElement(new TextElement("  UPDATE " + tableName + " SET"));
        //key
        introspectedTable.getAllColumns().forEach(introspectedColumn -> {
            String name = introspectedColumn.getActualColumnName();
            String javaName = introspectedColumn.getJavaProperty();
            String value = "    " + name + " = #{" + javaName + "}";
            if (introspectedTable.getAllColumns().indexOf(introspectedColumn) != columnsSize - 1) {
                value += ",";
            }
            updateSql.addElement(new TextElement(value));
        });
        updateSql.addElement(new TextElement("WHERE id = #{id}"));


        //delete
        XmlElement deleteSql = new XmlElement("update");
        deleteSql.addAttribute(new Attribute("id", "delete"));
        deleteSql.addElement(new TextElement("  UPDATE " + tableName + " SET"));
        deleteSql.addElement(new TextElement("  del_flag = #{DEL_FLAG_DELETE}"));
        deleteSql.addElement(new TextElement("  WHERE id = #{id}"));
        //将自带的生成sql全部删除
        document.getRootElement().getElements().removeIf(element -> true);
        XmlElement parentElement = document.getRootElement();
        parentElement.addElement(new TextElement("<!--通用映射-->"));
        parentElement.addElement(columnsSql);
        parentElement.addElement(new TextElement("<!--通用joins-->"));
        parentElement.addElement(joinsSql);
        parentElement.addElement(new TextElement("<!--通过ID查询-->"));
        parentElement.addElement(getSql);
        parentElement.addElement(new TextElement("<!--带分页的条件查询，可以在where进行逻辑判断-->"));
        parentElement.addElement(findListSql);
        parentElement.addElement(new TextElement("<!--查询全部-->"));
        parentElement.addElement(findAllListSql);
        parentElement.addElement(new TextElement("<!--通用插入-->"));
        parentElement.addElement(insertSql);
        parentElement.addElement(new TextElement("<!--通用更新-->"));
        parentElement.addElement(updateSql);
        parentElement.addElement(new TextElement("<!--根据ID进行删除-->"));
        parentElement.addElement(deleteSql);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }


    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass,
                                       IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable,
                                       ModelClassType modelClassType) {
        return !fields.contains(field.getName().toLowerCase());
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        if (fields.contains(method.getName().substring(3).toLowerCase())) {
            return false;
        }
        return true;
    }


    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        if (fields.contains(method.getName().substring(3).toLowerCase())) {
            return false;
        }
        return true;
    }
}
