package de.workshops.bookshelf;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;

public class IsbnLookupProperties {

    @Getter
    @Setter
    private URI serverUri;
}
