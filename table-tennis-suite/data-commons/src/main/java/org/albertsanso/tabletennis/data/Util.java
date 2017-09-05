package org.albertsanso.tabletennis.data;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.text.Normalizer;

public class Util {
    public static String buildCodeName(String name) {
        return normalize(name);
    }

    public static String normalize(String str) {
        return Normalizer.normalize(str.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^\\p{Alnum}]+", "-")
                .replaceAll("[^a-zA-Z0-9]+$", "-")
                .replaceAll("^[^a-zA-Z0-9]+", "-");
    }

    public static int getDistance(String a, String b) {
        return LevenshteinDistance.getDefaultInstance().apply(a, b);
    }

    public static boolean matchStrings(String str1, String str2, int percent) {

        String normalizedString1 = normalize(str1);
        String normalizedString2 = normalize(str2);

        int distance = Util.getDistance(normalizedString1, normalizedString2);

        double meanLength = (normalizedString1.length() + normalizedString2.length())/2;
        int minimumDistance = (int) ((meanLength*(100-percent))/100)+1;

        return distance < minimumDistance;
    }

    public static void main(String[] args) {

        System.out.println(matchStrings("", "", 100));
        System.out.println(matchStrings("Terrassa", "Rubi", 30));
        System.out.println(matchStrings("Terrassa", "terrass", 85));
        System.out.println(matchStrings("APDO. CORREOS 60", "APDO. DE CORREOS, 60", 81));
        System.out.println(matchStrings("APDO. DE CORREOS 60", "APDO. DE CORREOS, 60", 100));
        System.out.println(matchStrings("PILAR PÉREZ LAVID,2-ESC.1-3º B", "C/ PILAR PEREZ LAVID, 2-ESC 1-3ºB", 40));
        System.out.println(matchStrings("aluche-t-m-", "aluche-tenis-de-mesa", 30));
        System.out.println(matchStrings("agrupacion-deportiva-tenis-mesa-leganes-1", "asociacion-deportiva-de-tenis-de-mesa-leganes-1", 40));
    }
}