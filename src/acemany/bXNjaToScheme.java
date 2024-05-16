package acemany;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import mindustry.Vars;
import mindustry.core.ContentLoader;
import mindustry.game.Schematic;
import mindustry.type.ItemSeq;
import mindustry.type.ItemStack;


public class bXNjaToScheme{
    private static String schematic = "bXNjaAF4nGNgZ2BmZmDJS8xNZbAyvNhwYeuFDRd2XWy6sO9ig8KF7UDO5osNF5svbLuwASp4sfHCPiBxsQmoeN+FnRd2gUgG7pTU4uSizIKSzPw8Bnu49I6LTQoXdqAZdGErUBTdqk0KQJM2AUW2XNjAwJaTmJSaU8zAGv1+/oZYFgbenPz0zGTdlMzigpzESgb+3MzkonzdgqL85NTi4vwiBu7MktRc3eL80qLkVAb+koz8oszSXN2i1MTkEqAsAwMrAwMjEAIRMwMDH1BgasWc2ku+fIcUBNoeHpz6UvMZh0O3/faCl9MuSEmrX3KQvnx69UK26fFsZp9bzkqLXHx3S/3/rpOT3t/prxTaP2vZGosTD4Sjn/8Ln56pJi+n6OUzp23PiTNpzb+0vO6+trKf+NEyZtGaOy+Lok4/OyH2gnFV8y3H3IVRK9hWuVW5VThxOB/0MxBew6L80E1aaOKi2r/B881X6scEL2sAu48R6D5Gz4o5vUGBfi0GArq/Uy2OxhzgFTj7b5JE/gZxjZKy7XkzlbZwHf/i5qep0X1MQijq3ux956p9lI68O7xNYWpTWsOl2BJ7h4nzLkr6hr9Q8K4uecDp5X/rI/vXbc3cXsdTJK9NOS/95vdjV6GIrpht9ku2ql6PZPbYZLRix7ybOjbdZ06eKBZpM3pzWfz+lgnpDevjpOwc7s3gPSEl3LUkbdmMmZ65h20ZIr/bXPw4r/P7VDVW1cNZmUunPr7Emd879dXJqqLAZREnWw5pbPqxeo/Z/Y+9iRvnfjtb276pbI2sTH2o+Tfe8KM3dX+vk1xmul9/Add+RbNJ5+MkT6nPtnc+t31W/f0TdX2funjy8v+EG7XHT+KsrUzOfmnQtvglz9a3AjHPBU/d2qb7yaTCvOU6h7pYSLCrFf/jB3eMevu5zdZtjC5faHPh4mwBJcHy4l87b3Q+EY40+uHJwAQMUyZgAgAmdGA6ACYCAC9SYX4=";


    private static String fixEnc(String string) throws UnsupportedEncodingException{
        return new String(string.getBytes("windows-1251"), "utf8");
    }

    private static String requirementsToString(ItemSeq itemseq){
        StringBuilder output = new StringBuilder();
        for (ItemStack itemStack : itemseq) {
            output.append(itemStack.item.name + ": " + itemStack.amount + "\n");
        }
        return output.toString();
    }

    public static void main(String[] args) throws IOException, UnsupportedEncodingException{
        String input = System.console().readLine();

        Vars.content = new ContentLoader();
        Vars.content.createBaseContent();
        Schematic scheme = ContentHandler.parseSchematic(input.isEmpty() ? schematic : input);
        String output = fixEnc("Название: ") + scheme.name() + "\n" +
                        fixEnc("Описание:\n") + scheme.description() + "\n" +
                        fixEnc("Теги: ") + scheme.labels + "\n" +
                        fixEnc("Версия: ") + scheme.tags.get("version") + "\n" +
                        fixEnc("Размер: ") + scheme.width + "x" + scheme.height + "\n\n" +
                        requirementsToString(scheme.requirements()) + "\n" +
                        fixEnc("Энергия: +") + scheme.powerProduction()*60 + " -" + scheme.powerConsumption()*60;
        System.out.println(output);
    }
}
