package Enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDocumentoEnum {


    NOTIFICACAO(30L),

    PARECER_DE_DEFERIMENTO(40L),

    PARECER_DE_INDEFERIMENTO_POR_DECISAO_DO_INTERESSADO(45L),

    TERMO_DE_DECLARACAO(69L);


    private Long id;
}
