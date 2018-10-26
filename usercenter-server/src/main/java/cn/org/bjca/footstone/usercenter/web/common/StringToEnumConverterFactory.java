package cn.org.bjca.footstone.usercenter.web.common;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/15
 * @since 1.0
 */
@SuppressWarnings("ALL")
@Component
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

  private static class StringToEnumConverter<T extends Enum>
      implements Converter<String, T> {

    private Class<T> enumType;

    private StringToEnumConverter(Class<T> enumType) {
      this.enumType = enumType;
    }

    @Override
    public T convert(String source) {
      return (T) Enum.valueOf(this.enumType, source.trim());
    }
  }

  @Override
  public <T extends Enum> Converter<String, T> getConverter(
      Class<T> targetType) {
    return new StringToEnumConverter(targetType);
  }
}
