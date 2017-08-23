package com.example;

import cn.vpclub.moses.utils.validator.AbstractGenericParameter;
import cn.vpclub.moses.utils.validator.annotations.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FileComplaintCommand extends AbstractGenericParameter<String> {
    @NotEmpty
    private final String company;
    @NotEmpty
    private final String description;
}
