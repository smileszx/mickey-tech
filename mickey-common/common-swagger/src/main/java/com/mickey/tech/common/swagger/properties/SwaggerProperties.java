package com.mickey.tech.common.swagger.properties;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Title: swagger配置参数
 * Description: swagger配置参数
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "swagger")
@Component
public class SwaggerProperties {

	/**
	 * 是否开启swagger,默认开启
	 */
	private Boolean enabled = true;

	/**
	 * API-DOC的标题
	 */
	private String title;

	/**
	 * API-DOC的描述
	 */
	private String description;

	/**
	 * API-DOC的版本
	 */
	private String version;

	/**
	 * API-DOC的扫描包名前缀
	 */
	private String basePackage;
	
	/**
	 * API-DOC联系人信息
	 */
	private Contact contact = new Contact();

	@Data
	@NoArgsConstructor
	public static class Contact {

		/**
		 * 联系人
		 **/
		private String name = "";
		/**
		 * 联系人url
		 **/
		private String url = "";
		/**
		 * 联系人email
		 **/
		private String email = "";

	}
	
	/**
	 * API接口中header中token的名字,默认名字为'令牌'
	 */
	private String headTokenDescription="令牌";
}