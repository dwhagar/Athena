package com.blazingumbra.athena.wiki.interaction;

import com.google.gson.Gson;

abstract class AbstractWikiInteraction {
    static Gson gson;

    AbstractWikiInteraction() {
        gson = new Gson();
    }
}
