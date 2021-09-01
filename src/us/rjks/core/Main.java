package us.rjks.core;

import us.rjks.utils.MorseCodierung;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 01.09.2021 / 16:27
 *
 **************************************************************************/

public class Main {

    public static void main(String[] args) {
        MorseCodierung morseCodierung = new MorseCodierung();

        System.out.println(morseCodierung.translate("Hello World"));
    }

}
