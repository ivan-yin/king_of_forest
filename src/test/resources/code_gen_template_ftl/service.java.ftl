package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment} 服务类:${table.serviceName}
 * </p>
 *
 * @author ${author}
 * @since ${date}
* @version 1.0
* @copyright ifzer.com
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>
