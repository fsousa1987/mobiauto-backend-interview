package com.francisco.backend.mobiauto.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * Configuração de Jackson para suporte ao Spring Data Web.
 * <p>
 * Esta classe configura o suporte ao Spring Data Web, permitindo a serialização de páginas
 * utilizando DTOs.
 * </p>
 *
 * <p>
 * A anotação {@link EnableSpringDataWebSupport} ativa o suporte ao Spring Data Web, permitindo a configuração
 * de páginas, sorters e outras funcionalidades relacionadas ao Spring Data diretamente nas requisições web.
 * </p>
 *
 * <p>
 * O atributo {@code pageSerializationMode} define o modo de serialização de páginas como {@code VIA_DTO},
 * o que significa que as páginas serão serializadas utilizando DTOs em vez das entidades diretamente.
 * </p>
 *
 * <p>
 * Isso se fez necessário pois estava logando este WARN na aplicação: Serializing PageImpl instances as-is is not supported,
 * meaning that there is no guarantee about the stability of the resulting JSON structure!
 * For a stable JSON structure, please use Spring Data's PagedModel
 * (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
 * </p>
 */
@Configuration
@EnableSpringDataWebSupport(
        pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO
)
public class JacksonConfig {
}
