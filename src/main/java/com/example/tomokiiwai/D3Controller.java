package com.example.tomokiiwai;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * コントローラー
 *
 * @author tomoki.iwai
 */
@Controller
public class D3Controller {
	private final JavaMailSender mailSender;
	private final TemplateEngine templateEngine;

	/**
	 * Constructor
	 */
	public D3Controller(final JavaMailSender mailSender, final TemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}

	/**
	 * トップページ
	 */
	@RequestMapping("/")
	public String index() {
		return "/index.html";
	}

	/**
	 * メール送信
	 */
	@RequestMapping("/sendMail")
	@ResponseBody
	public String sendMail() {
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();

		try {
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			// 件名
			message.setSubject("件名");
			// 送信者
			message.setFrom(new InternetAddress("from@example.com", "送信者名"));
			// 送信先
			message.setTo("to@example.com");
			// CC
			message.setCc("cc@example.com");
			// BCC
			message.setBcc("bcc@example.com");
			// 返信先
			message.setReplyTo("reply@example.com");

			final Context ctx = new Context(Locale.JAPAN);
			// 本文に埋め込む変数の例
			ctx.setVariable("hoge", "ほげ");

			// Thymeleafでテンプレートを描画
			final String textContent = templateEngine.process("mail/text/sample", ctx);
			final String htmlContent = templateEngine.process("mail/html/sample", ctx);
			message.setText(textContent, htmlContent);

			this.mailSender.send(mimeMessage);

			return "OK";
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
