package com.example.tomokiiwai;

import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Collections;

/**
 * テンプレートエンジン設定
 *
 * @author tomoki.iwai
 */
@Configuration
@Import(ThymeleafAutoConfiguration.class)
public class D3TemplateEngineConfig {
	/**
	 * テキスト用のテンプレートリゾルバー
	 */
	private static final ClassLoaderTemplateResolver TEXT_TEMPLATE_RESOLVER;
	/**
	 * HTML用のテンプレートリゾルバー
	 */
	private static final ClassLoaderTemplateResolver HTML_TEMPLATE_RESOLVER;

	static {
		// テキスト用テンプレートリゾルバーを初期化
		TEXT_TEMPLATE_RESOLVER = new ClassLoaderTemplateResolver();
		TEXT_TEMPLATE_RESOLVER.setOrder(1);
		TEXT_TEMPLATE_RESOLVER.setResolvablePatterns(Collections.singleton("mail/text/*"));
		TEXT_TEMPLATE_RESOLVER.setSuffix(".txt");
		TEXT_TEMPLATE_RESOLVER.setTemplateMode(TemplateMode.TEXT);
		TEXT_TEMPLATE_RESOLVER.setCharacterEncoding("UTF-8");
		TEXT_TEMPLATE_RESOLVER.setCacheable(false);

		// HTML用のテンプレートリゾルバーを初期化
		HTML_TEMPLATE_RESOLVER = new ClassLoaderTemplateResolver();
		HTML_TEMPLATE_RESOLVER.setOrder(2);
		HTML_TEMPLATE_RESOLVER.setSuffix(".html");
		HTML_TEMPLATE_RESOLVER.setTemplateMode("HTML5");
		HTML_TEMPLATE_RESOLVER.setCharacterEncoding("UTF-8");
		HTML_TEMPLATE_RESOLVER.setCacheable(false);
	}

	/**
	 * Thymeleafのテンプレートエンジンをカスタムに置換
	 */
	@Bean
	public TemplateEngine templateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(TEXT_TEMPLATE_RESOLVER);
		templateEngine.addTemplateResolver(HTML_TEMPLATE_RESOLVER);
		return templateEngine;
	}
}
