package cn.org.bjca.footstone.usercenter.util;

import com.github.bingoohuang.patchca.background.SingleColorBackgroundFactory;
import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.service.AbstractCaptchaService;
import com.github.bingoohuang.patchca.text.renderer.BestFitTextRenderer;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/27
 * @since 1.0
 */
@Slf4j
public abstract class CaptchaGenerator {

  private static final DefaultCaptchaService SERVICE = new DefaultCaptchaService();

  public static Pair<String, String> generate() {
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    try {
      String patchca = EncoderHelper.getChallangeAndWriteImage(SERVICE, "png", b);
      return Pair.of(patchca, Base64.encode(b.toByteArray()));
    } catch (IOException e) {
      log.error("生成验证码异常!");
    }
    return null;
  }

  static class DefaultCaptchaService extends AbstractCaptchaService {

    public DefaultCaptchaService() {
      wordFactory = new DefaultWordFactory();
      //字体
      fontFactory = new RandomFontFactory();
      //效果
      textRenderer = new BestFitTextRenderer();
      //背景
      backgroundFactory = new SingleColorBackgroundFactory();
      //字体颜色
      colorFactory = new SingleColorFactory(new Color(25, 60, 170));
      //样式(曲线波纹加干扰线)
      filterFactory = new CurvesRippleFilterFactory(colorFactory);
//图片长宽
      width = 150;
      height = 50;
    }
  }


  private static class DefaultWordFactory extends RandomWordFactory {

    public DefaultWordFactory() {
      characters = "absdekmnowx23456789";
      minLength = 6;
      maxLength = 8;
    }
  }
}
