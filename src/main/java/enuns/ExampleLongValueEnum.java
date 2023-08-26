package enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExampleLongValueEnum {

    KEY_1(1L),

    KEY_2(2L);

    private final Long id;
}