package org.example;

import java.util.regex.Pattern;

public class RegexDemo {

    private final static int MAX_LOOPS = 30000;
    public static void main(String[] args) {
        String[] s = getArray();

        String regex = ".*(nl-NL|nl-BE|fr-BE|en-GB|de-DE).*";

        regexNotCompiledValidation(s, regex);
        regexCompiledValidation(s, regex);
        codeValidation(s);
    }

    private static void codeValidation(String[] s) {
        int validCountCode = 0;
        for (int i = 0; i < MAX_LOOPS; i++) {
            for (String value : s) {
                if (containsValidLanguageHeader(value)) {
                    validCountCode++;
                }
            }
        }
        System.out.println("Valid count using code " + validCountCode);
    }

    public static boolean containsValidLanguageHeader(String s) {
        // validates the expression ".*(nl-NL|nl-BE|fr-BE|en-GB|de-DE).*"
        return (s.contains("nl-NL") || s.contains("nl-BE") || s.contains("fr-BE") || s.contains("en-GB") || s.contains("de-DE"));
    }

    private static void regexCompiledValidation(String[] s, String regex) {
        int validCountRegexCompiled = 0;
        Pattern p = Pattern.compile(regex);
        for (int i = 0; i < MAX_LOOPS; i++) {
            for (String value : s) {
                if (p.matcher(value).matches()) {
                    validCountRegexCompiled++;
                }
            }
        }
        System.out.println("Valid count in regex compiled " + validCountRegexCompiled);
    }

    private static void regexNotCompiledValidation(String[] s, String regex) {
        int validCountRegexNotCompiled = 0;
        for (int i = 0; i < MAX_LOOPS; i++) {
            for (String value : s) {
                if (value.matches(regex)) {
                    validCountRegexNotCompiled++;
                }
            }
        }
        System.out.println("Valid count using regex not compiled " + validCountRegexNotCompiled);
    }



    private static String[] getArray() {
        return new String[]{
                "BE12345678",
                "ES12345678",
                "NL12345678",
                "NL00000000",
                "NL000A0000",
                "UK12345678",
                "ES123456789",
                "A0123456789",
                "",
                "000",
                "5555555555555555555555555555555555555555dfdfdfdfdfdfdYYtttttt",
                "5555555555555555555555555555555555555555dfdfdfdfdfdfdYYtttdfdffdfdtttdfdfdfdfttttttt",
                "A0123456789sd sad sa fdg sfgfgh sfh fdh fdh dhgh gfh gdfh gfdh gh xsdfsfdsfds gfg fg ssdfsdfdsf sdf 4445455554545g sg NL000A0000NL000A0000NL000A0000NL000A0000",
                "ES12345678s A0123456789 A0123456789 A0123456789 A0123456789 d sad sa fdg sfgfgh sfh fdh fdh dhgh gfh gdfh gfdh gh xsdfsfdsfds gfg fg ssdfsdfdsf sdf 4445455554545g sg NL000A0000NL000A0000NL000A0000NL000A0000",
                "    sff f df  df fd  dgfghfhfhg g gh g hghg235 g jhg jg jhghj g  hg jhg jhg jkhg jkhg jkhg jkhg jhkg jkh gj h",
                "    234445 trgrgh5 jhg jhg jkhg jkhg kjg kjhjkh hkj hhjg h ",
                "    cvvcvc gf gf gf  gf gf gfgf gf gfgf gf gfgf gf gfgf gf gf <script> ",
                "<script>     cvvcvc gf gf gf  gf gf gfgf gf gfgf gf gfgf gf gfgf gf gf ",
                "1+1",
                "=",
                "A0123456789sd sad sa fdg sfgfgh sfh fdh fdh dhgh gfh gdfh gfdh gh xsdfsfdsfds gfg fg ssdfsdfdsf sdf 4445455554545g sg NL000A0000NL000A0000NL000A0000NL000A0000",
                "ES12345678s A0123456789 A0123456789 A0123456789 A0123456789 d sad sa fdg sfgfgh sfh fdh fdh dhgh gfh gdfh gfdh gh xsdfsfdsfds gfg fg ssdfsdfdsf sdf 4445455554545g sg NL000A0000NL000A0000NL000A0000NL000A0000",
                "    sff f df  df fd  dgfghfhfhg g gh g hghg235 g jhg jg jhghj g  hg jhg jhg jkhg jkhg jkhg jkhg jhkg jkh gj h",
                "    234445 trgrgh5 jhg jhg jkhg jkhg kjg kjhjkh hkj hhjg h ",
                "    cvvcvc gf gf gf  gf gf gfgf gf gfgf gf gfgf gf gfgf gf gf <script> ",
                "<script>     cvvcvc gf gf gf  gf gf gfgf gf gfgf gf gfgf gf gfgf gf gf ",
                "thisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornot",
                " thisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornot",
                "thisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornot ",
                "thisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidor notthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornotthisisalongtetsthatmightbevalidornot ",
                "this is valid nl-NL",
                "this is not valid NL-NL",
                "en-GB this is also valid",
                "EN-gb this is not valid",
        };
    }
}