package cn.org.bjca.footstone.usercenter.dao.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntInfoAccountJoin extends EntInfo {

  private Long uid;
}