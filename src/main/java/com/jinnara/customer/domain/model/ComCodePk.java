package com.jinnara.customer.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ComCodePk implements Serializable {
  String groupId;
  String codeId;
}
