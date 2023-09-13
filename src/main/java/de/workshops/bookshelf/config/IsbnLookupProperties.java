package de.workshops.bookshelf.config;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;

public class IsbnLookupProperties {

    @Getter
    @Setter
    private URI serverUri;
}
