package acemany;

import java.io.IOException;

import mindustry.Vars;
import mindustry.core.ContentLoader;
import mindustry.game.Schematic;
import mindustry.game.Schematic.Stile;
import mindustry.io.JsonIO;
import mindustry.world.blocks.logic.LogicBlock;


public class bXNjaToScheme{
    public static void main(String[] args) throws IOException{
        String schematic = "bXNjaAF4nGNgZGBiZmDJS8xNZWBOysxj4E5JLU4uyiwoyczPY2BgYMtJTErNKWZgio5lYuDPzUwuytctKMpPTi0uzi9iYM8F0onpqUCFTAwgwAfEHRVzQvsu8h4y4HG5bD9FM49pigKj8AZG7h1dmc2XBB6FdWRGiC4Xlf/z80/N89c9hvIPJTuN92zO7Sn4FhigyR5S8eXbPh9WdZ6pmc+qZDXj53Xd+bdDyoX39UnJKXFfptk/i5ZoXbAz0HWl4esqY5kv6z24Q5aJmlV5d/fMmHLhwL2so+fe1G1aqe8QZrqbgRHoGkYWRgYuA0MwNDAwZAAAw8dWcQ==";
        String input = System.console().readLine();

        Vars.content = new ContentLoader();
        Vars.content.createBaseContent();
        Schematic scheme = ContentHandler.parseSchematic(input.isEmpty() ? schematic : input);

        for (Stile tile : scheme.tiles) {
            String config = tile.block instanceof LogicBlock ? ContentHandler.readCompressed((byte[])tile.config, true, tile):
                                                    (tile.config == null ? "null" : tile.config.toString());
            System.out.println("block: " + JsonIO.print(JsonIO.write(tile.block)) + "\n" +
                               "config: \"\"\"" + config + "\"\"\"\n------------------");
        }

        System.out.println("Title: \"" + scheme.name() + "\"\n" +
                           "Description: \"\n" + scheme.description() + "\"\n" +
                           "Tags: " + scheme.labels + "\n" +
                           "Version: " + scheme.tags.get("version") + "\n" +
                           "Size: " + scheme.width + "x" + scheme.height + "\n\n" +
                           ContentHandler.requirementsToString(scheme.requirements()) + "\n" +
                           "Energy: +" + scheme.powerProduction()*60 + " -" + scheme.powerConsumption()*60);
    }
}
