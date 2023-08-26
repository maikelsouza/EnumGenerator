package enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExampleStringValueEnum {

    KEY_1("value_1"),

    KEY_2("valeu_2");

    private final String id;
}