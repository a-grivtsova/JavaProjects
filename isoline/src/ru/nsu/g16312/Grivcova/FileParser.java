package ru.nsu.g16312.Grivcova;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ru.nsu.g16312.Grivcova.MyBiFunction.MyBiFunction;

public class FileParser {
    private static String[] splitLine(String line) {
        return line.trim().replace("/", " ").split(" ");
    }

    private static boolean needToSkip(String line) {
        String trimmed = line.trim();
        return (trimmed.isEmpty() || trimmed.startsWith("//"));
    }

    public static void parseFileAndChangeGlobalState(File f) throws IOException {
        int k, m;
        Color[] legendColors;

        try (Scanner sc = new Scanner(f)) {
            String line;
            String lines[];
            do {
                line = sc.nextLine();
            } while (needToSkip(line));
            int apnum = Integer.parseInt(splitLine(line)[0]);
            for (int i = 0; i < apnum; ++i) {
                lines = splitLine(sc.nextLine());
                if (lines.length < 2) throw new IOException("Unsupported config format");
                int x = Integer.parseInt(lines[0]);
                float y = Float.parseFloat(lines[1]);
            }
            do {
                line = sc.nextLine();
            } while (needToSkip(line));

            int epnum = Integer.parseInt(splitLine(line)[0]);
            for (int i = 0; i < epnum; ++i) {
                lines = splitLine(sc.nextLine());
                if (lines.length < 4) throw new IOException("Unsupported config format");
                int x = Integer.parseInt(lines[0]);
                int r = Integer.parseInt(lines[1]);
                int g = Integer.parseInt(lines[2]);
                int b = Integer.parseInt(lines[3]);
            }

            do {
                line = sc.nextLine();
            } while (needToSkip(line));

            int chnum = Integer.parseInt(line.trim().split(" ")[0]);
            for (int i = 0; i < chnum; ++i) {
                lines = splitLine(sc.nextLine());
                if (lines.length < 4) throw new IOException("Unsupported config format");
                float x = Float.parseFloat(lines[0]);
                float y = Float.parseFloat(lines[1]);
                float z = Float.parseFloat(lines[2]);
                float q = Float.parseFloat(lines[3]);
            }
        } catch (NoSuchElementException e) {
            throw new IOException("Unsupported config format");
        } catch (NumberFormatException e) {
            throw new IOException("Unsupported config format");
        }

    }


}
