package de.workshops.bookshelf.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "bookshelf")
@Validated
public class BookshelfProperties {

    @Getter
    @Setter
    @NotBlank
    private String owner;

    @Getter
    @NestedConfigurationProperty
    private final IsbnLookupProperties isbnLookup = new IsbnLookupProperties();
}
