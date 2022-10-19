package com.example.recipe_project.service;


import com.example.recipe_project.enums.EnumUnit;
import com.example.recipe_project.model.AppUser;
import com.example.recipe_project.model.Ingredient;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.enums.EnumDifficulty;
import com.example.recipe_project.repo.AppUserRepo;
import com.example.recipe_project.repo.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;


@Service
public class TestDataLoader {
    private final RecipeRepo repo;
    private final AppUserService appUserService;
    private final RecipeService recipeService;

    @Autowired
    public TestDataLoader(RecipeRepo recipeRepo, AppUserService appUserService, RecipeService recipeService) {
        this.repo = recipeRepo;
        this.appUserService = appUserService;
        this.recipeService = recipeService;
    }


    public void loadRecipes() throws Exception {

        /*Recipe r1 = new Recipe("Házi vaniliás túrókrém",
        EnumDifficulty.NAGYON_KONNYU, 40, false, false, true,
        "\n25 dkg túró \n2 db tojás (sárgája) \n2 dl tej \n5-8 dkg cukor \n1 ek vaníliás pudingpor \n1/2 rúd vanília magjai \n1 csipet só",
        "A tojások sárgáját a cukorral, pudingporral és egy kevés tejjel alaposan elkeverjük (előzőleg a sárgájákat a
        cukorral kissé habosítjuk). A maradék tejet a vanília magjaival felforraljuk, beleöntjük a masszát, majd addig
        keverjük, amíg sűrű pudingot nem kapunk. Ezután hagyjuk kihűlni. A túrót áttörjük, majd hozzáöntjük a kihűlt
        vaníliás alapot, és botmixerrel addig keverjük, amíg krémes nem lesz. Poharakba, tálakba adagolva lehűtjük.
        Tálaláskor megszórhatjuk áfonyával, vagy készíthetünk hozzá karamell vagy gyümölcs öntetet.");*/

        List<Ingredient> ingredientsr1 = new ArrayList<>();

        Recipe r1 = new Recipe(
                "Házi vanilliás túrókrém",
                EnumDifficulty.VERY_EASY,
                40,
                false,
                false,
                true,
                ingredientsr1,
                "A tojások sárgáját a cukorral, pudingporral és egy kevés tejjel alaposan elkeverjük, (előzőleg a " +
                        "sárgájákat a cukorral kissé habosítjuk). A maradék tejet a vanília magjaival felforraljuk, " +
                        "beleöntjük a masszát, majd addig keverjük, amíg sűrű pudingot nem kapunk. Ezután hagyjuk kihűlni. " +
                        "A túrót áttörjük, majd hozzáöntjük a kihűlt vaníliás alapot, és botmixerrel addig keverjük, " +
                        "amíg krémes nem lesz. Poharakba, tálakba adagolva lehűtjük. Tálaláskor megszórhatjuk áfonyával, " +
                        "vagy készíthetünk hozzá karamell vagy gyümölcs öntetet.)",
                "JPG",
                "vanilias_kremturo",
                photosToArray("vanilias_kremturo.jpg")
        );

        ingredientsr1.addAll(Arrays.asList(
                new Ingredient("túró", 25, EnumUnit.DKG, r1),
                new Ingredient("tojás", 2, EnumUnit.PCS, r1),
                new Ingredient("tej", 0.2, EnumUnit.L, r1),
                new Ingredient("cukor", 7, EnumUnit.DKG, r1),
                new Ingredient("vaníliás pudingpor", 1, EnumUnit.TBS, r1),
                new Ingredient("rúd vanília magjai", 0.5, EnumUnit.PCS, r1),
                new Ingredient("só", 1, EnumUnit.PINCH, r1)
        ));

        repo.save(r1);

//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r2 = new Recipe("Bögrés gofri",
        EnumDifficulty.KONNYU, 20, false, false, false,
        "\n3db tojás \n1.5 bögre tej \n2ek cukor \n0.75 bögre liszt \n0.25 bögre olaj \n1 csom vaníliás cukor \n1 csom sütőpor \n1 csipet só",
        "A tojásokat szétválasztjuk. Egy nagy tálban robotgéppel összekeverjük a tejet, a tojássárgáját, a cukrot, a
        lisztet, az olajat, a vaníliás cukrot, a sütőport és a sót. Kemény habbá verjük a tojásfehérjéket, majd óvatosan
        a masszához forgatjuk. A tésztát a hűtőben pihentetjük egy órát, majd gofrisütőben a szokásos módon kisütjük a gofrikat.");*/

        List<Ingredient> ingredientsr2 = new ArrayList<>();

        Recipe r2 = new Recipe(
                "Bögrés gofri",
                EnumDifficulty.EASY,
                20,
                false,
                false,
                false,
                ingredientsr2,
                "A tojásokat szétválasztjuk. Egy nagy tálban robotgéppel összekeverjük a tejet, a tojássárgáját, a " +
                        "cukrot, a lisztet, az olajat, a vaníliás cukrot, a sütőport és a sót. Kemény habbá verjük a " +
                        "tojásfehérjéket, majd óvatosan a masszához forgatjuk. A tésztát a hűtőben pihentetjük egy órát," +
                        " majd gofrisütőben a szokásos módon kisütjük a gofrikat.",
                "JPG",
                "bogres_gofri",
                photosToArray("bogres_gofri.jpg")
        );

        ingredientsr2.addAll(Arrays.asList(
                new Ingredient("tojás", 3, EnumUnit.PCS, r2),
                new Ingredient("tej", 1.5, EnumUnit.CUP, r2),
                new Ingredient("cukor", 2, EnumUnit.TBS, r2),
                new Ingredient("liszt", 0.75, EnumUnit.CUP, r2),
                new Ingredient("olaj", 0.25, EnumUnit.CUP, r2),
                new Ingredient("vaníliás cukor", 1, EnumUnit.PCS, r2),
                new Ingredient("sütőpor", 1, EnumUnit.PKG, r2),
                new Ingredient("só", 25, EnumUnit.PINCH, r2)
        ));

        repo.save(r2);

//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r3 = new Recipe("Kekszes-tejfölös süti sütés nélkül",
        EnumDifficulty.NORMALIS, 25, false, false, false,
        "\n50 dkg háztartási keksz \n2n pohár tejföl \n3csom vaníliás cukor \n9ek kristálycukor \n1ek keserű kakaópor",
        "Az egyik pohár tejfölbe belekeverünk 4 evőkanál cukrot és 2 csomag vaníliás cukrot. A másikba 5 evőkanál cukrot,
        1 csomag vaníliás cukrot, valamint 1 kanál kakaóport. Egy közepes méretű (kb. 20x30 cm-es) tepsi aljára tegyünk
        egy réteg háztartási kekszet. Kenjük el rajta a vaníliás tejföl 2/3 részét. Fedjük be egy réteg keksszel,
        egyengessük el rajta a kakaós tejföl 2/3-át. Borítsuk be egy újabb réteg keksszel, majd a maradék vaníliás és
        kakaós tejfölt öntsük a tetejére, egyengessük el, fogpiszkálóval tetszés szerint rajzoljunk bele mintákat.
        Tegyük hűtőbe 10-12 órára. A keksz megpuhul, a tejföl pedig kissé megköt.");*/

        List<Ingredient> ingredientsr3 = new ArrayList<>();

        Recipe r3 = new Recipe(
                "Kekszes-tejfölös süti sütés nélkül",
                EnumDifficulty.NORMAL,
                25,
                false,
                false,
                false,
                ingredientsr3,
                "Az egyik pohár tejfölbe belekeverünk 4 evőkanál cukrot és 2 csomag vaníliás cukrot. A másikba 5 " +
                        "evőkanál cukrot, 1 csomag vaníliás cukrot, valamint 1 kanál kakaóport. Egy közepes méretű " +
                        "(kb. 20x30 cm-es) tepsi aljára tegyünk egy réteg háztartási kekszet. Kenjük el rajta a vaníliás" +
                        " tejföl 2/3 részét. Fedjük be egy réteg keksszel, egyengessük el rajta a kakaós tejföl 2/3-át. " +
                        "Borítsuk be egy újabb réteg keksszel, majd a maradék vaníliás és kakaós tejfölt öntsük a " +
                        "tetejére, egyengessük el, fogpiszkálóval tetszés szerint rajzoljunk bele mintákat. Tegyük hűtőbe" +
                        " 10-12 órára. A keksz megpuhul, a tejföl pedig kissé megköt.",
                "JPG",
                "kekszes_tejfolos_suti",
                photosToArray("kekszes_tejfolos_suti.jpg")
        );

        ingredientsr3.addAll(Arrays.asList(
                new Ingredient("háztartási keksz", 50, EnumUnit.DKG, r3),
                new Ingredient("tejföl", 2, EnumUnit.CUP, r3),
                new Ingredient("vaníliás cukor", 3, EnumUnit.PKG, r3),
                new Ingredient("kristálycukor", 9, EnumUnit.TBS, r3),
                new Ingredient("keserű kakaópor", 1, EnumUnit.TBS, r3)
        ));

        repo.save(r3);

//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r4 = new Recipe("BÖGRÉS KEFIRES-MEGGYES KEVERT SÜTI",
        EnumDifficulty.NAGYON_NEHEZ, 50, false, false, true,
        "\n3bögre liszt \n1csom sütőpor \n5bögre cukor \n1csom vaníliás cukor \n1bögre kefir \n1bögre olaj \n4db tojás
        \n40dkg meggy \n1ek fahéj \nvaj \nporcukor\n \nfahéj",
        "Egy 175 ml-es kefires dobozzal kimérjük a hozzávalókat. Összekeverjük a lisztet, a sütőport, a cukrot és a
        vaníliás cukrot. Hozzáadjuk a kefirt, az olajat és a tojást, és alaposan összekeverjük. A tésztát kivajazott
        és kilisztezett tepsibe öntjük. A lecsögpögtetett meggyet megszórjuk a fahéjjal, majd a tésztára szórjuk. 180
        fokra előmelegített sütőben 30 perc alatt készre sütjük, porcukorral a tetején tálaljuk."*/

        List<Ingredient> ingredientsr4 = new ArrayList<>();

        Recipe r4 = new Recipe(
                "Bögrés kefíres-meggyes kevert süti",
                EnumDifficulty.VERY_DIFFICULT,
                50,
                false,
                false,
                true,
                ingredientsr4,
                "Egy 175 ml-es kefires dobozzal kimérjük a hozzávalókat. Összekeverjük a lisztet, a sütőport, a cukrot " +
                        "és a vaníliás cukrot. Hozzáadjuk a kefirt, az olajat és a tojást, és alaposan összekeverjük. " +
                        "A tésztát kivajazott és kilisztezett tepsibe öntjük. A lecsögpögtetett meggyet megszórjuk a " +
                        "fahéjjal, majd a tésztára szórjuk. 180 fokra előmelegített sütőben 30 perc alatt készre sütjük, " +
                        "porcukorral a tetején tálaljuk.",
                "JPG",
                "bogres_kefires_meggyes",
                photosToArray("bogres_kefires_meggyes.jpg")
        );

        ingredientsr4.addAll(Arrays.asList(
                new Ingredient("liszt", 1, EnumUnit.CUP, r4),
                new Ingredient("sütőpor", 1, EnumUnit.PKG, r4),
                new Ingredient("cukor", 5, EnumUnit.CUP, r4),
                new Ingredient("vaníliás cukor", 1, EnumUnit.PKG, r4),
                new Ingredient("kefir", 1, EnumUnit.CUP, r4),
                new Ingredient("olaj", 1, EnumUnit.CUP, r4),
                new Ingredient("tojás", 4, EnumUnit.PCS, r4),
                new Ingredient("meggy", 40, EnumUnit.DKG, r4),
                new Ingredient("fahéj", 1, EnumUnit.TBS, r4),
                new Ingredient("porcukor", 0.0, EnumUnit.PCS, r4),
                new Ingredient("vaj", 0, EnumUnit.PCS, r4),
                new Ingredient("fahéj", 0, EnumUnit.PCS, r4)
        ));

        repo.save(r4);

//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r5 = new Recipe("EGYSZERŰ TÚRÓS KÓKUSZGOLYÓ",
        EnumDifficulty.NAGYON_KONNYU, 20, false, false, true,
        "\n25dkg túró \n20dkg porcukor \n15dkg kókuszreszelék \n1csom vaníliás cukor \n1db citrom",
        "Egy tálban áttörjük a túrót, hozzáadjuk a porcukrot és a vaníliás cukrot.
        " A kókuszreszelékből félreteszünk egy keveset, a többit a keverékhez adjuk. Ezután megmossuk és szárazra
        töröljük a citromot, és a masszához adjuk a lereszelt a héját. A hozzávalókat alaposan összegyúrjuk, majd kis
        gombócokat formálunk belőle. A végén a golyókat meghempergetjük a maradék kókuszreszelékben.");*/

        List<Ingredient> ingredientsr5 = new ArrayList<>();

        Recipe r5 = new Recipe(
                "Egyszerű tűrós kókuszgolyó",
                EnumDifficulty.VERY_DIFFICULT,
                50,
                false,
                false,
                true,
                ingredientsr5,
                "A kókuszreszelékből félreteszünk egy keveset, a többit a keverékhez adjuk. Ezután megmossuk és " +
                        "szárazra töröljük a citromot, és a masszához adjuk a lereszelt a héját. A hozzávalókat " +
                        "alaposan összegyúrjuk, majd kis gombócokat formálunk belőle. A végén a golyókat meghempergetjük " +
                        "a maradék kókuszreszelékben.",
                "JPG",
                "turoskokuszgolyo",
                photosToArray("turoskokuszgolyo.jpg")
        );

        ingredientsr5.addAll(Arrays.asList(
                new Ingredient("túró", 25, EnumUnit.DKG, r5),
                new Ingredient("porcukor", 20, EnumUnit.DKG, r5),
                new Ingredient("kókuszreszelék", 15, EnumUnit.DKG, r5),
                new Ingredient("vaníliás cukor", 1, EnumUnit.PKG, r5),
                new Ingredient("citrom", 1, EnumUnit.PCS, r5)
        ));

        repo.save(r5);

//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r6 = new Recipe("KÓKUSZTEKERCS",
        EnumDifficulty.NORMALIS, 30, false, false, true,
        "\n50dkg darált keksz \n50dkg porcukor \n6ek kakaópor \n6ek rum \n1db tojássárgája \n5ek tej \n20dkg vaj
        \n1tasak vaníliás cukor \n20dkg kókuszreszelék",
        "Alaposan összegyúrjunk a darált kekszet a porcukorral, a kakaóporral és a rummal,\n" +
        "a tojássárgájával és tejjel, majd a hűtőbe tesszük, kb. 1 órára. A puha vajat krémesre keverjük a vaníliás
        cukorral, majd apránként hozzáadjuk a kókuszreszeléket. A porcukorral megszórt sütőpapíron kinyújtjuk a kekszes
        masszát. Rákenjük a kókuszos krémet, és feltekerjük a sütőpapír segítségével. A tekercset egy kevés porcukorban
        és kókuszreszelékben megforgatjuk, majd alufóliába csomagoljuk, és hűtőbe tesszük, amíg megszilárdul. Meleg
        vízbe mártott, megtörölt késsel kb. 1 cm-es szeletekre vágjuk.");*/

        List<Ingredient> ingredientsr6 = new ArrayList<>();

        Recipe r6 = new Recipe(
                "Kókusztekercs",
                EnumDifficulty.NORMAL,
                30,
                false,
                false,
                true,
                ingredientsr6,
                "Alaposan összegyúrjunk a darált kekszet a porcukorral, a kakaóporral és a rummal, a tojássárgájával " +
                        "és tejjel, majd a hűtőbe tesszük, kb. 1 órára. A puha vajat krémesre keverjük a " +
                        "vaníliás cukorral, majd apránként hozzáadjuk a kókuszreszeléket. A porcukorral megszórt " +
                        "sütőpapíron kinyújtjuk a kekszes masszát. Rákenjük a kókuszos krémet, és feltekerjük a " +
                        "sütőpapír segítségével. A tekercset egy kevés porcukorban és kókuszreszelékben megforgatjuk, " +
                        "majd alufóliába csomagoljuk, és hűtőbe tesszük, amíg megszilárdul. Meleg vízbe mártott, " +
                        "megtörölt késsel kb. 1 cm-es szeletekre vágjuk.",
                "JPG",
                "kokusztekercs",
                photosToArray("kokusztekercs.jpg")
        );

        ingredientsr6.addAll(Arrays.asList(
                new Ingredient("darált keksz", 50, EnumUnit.DKG, r6),
                new Ingredient("porcukor", 50, EnumUnit.DKG, r6),
                new Ingredient("kakaópor", 6, EnumUnit.TBS, r6),
                new Ingredient("rum", 6, EnumUnit.TBS, r6),
                new Ingredient("tojássárgája", 1, EnumUnit.PCS, r6),
                new Ingredient("tej", 5, EnumUnit.TBS, r6),
                new Ingredient("vaj", 20, EnumUnit.DKG, r6),
                new Ingredient("vaníliás cukor", 1, EnumUnit.PKG, r6),
                new Ingredient("kókuszreszelék", 20, EnumUnit.DKG, r6)
        ));

        repo.save(r6);

//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r7 = new Recipe("PUDINGOS KEKSZEK",
        EnumDifficulty.NEHEZ, 30, true, true, false,
        "\n25dkg margarin \n10dkg porcukor \n1ek vaníliás cukor \n25dkg finomliszt \n2csom pudingpor \ncsokicsepp",
        "Egy keverőtálban, konyhai robotgép segítségével minden alapanyagot összegyúrunk, majd kis golyókat formálunk a
         masszából. Sütőpapírral bélelt tepsire rakosgatjuk őket, majd villávalkissé lenyomjuk - ezzel mintegy mintázzuk
          is a felületüket. De ellapíthatjuk atenyererünkkelis, majd 3-3 csokicseppet vagy apróra vágott étcsokidarabot
           belenyomhatunka tésztába. 200 fokra előmelegített sütőben max. 10 perc alatt készre sütjük a kekszecskéinket!");*/

        List<Ingredient> ingredientsr7 = new ArrayList<>();

        Recipe r7 = new Recipe(
                "Pudingos kekszek",
                EnumDifficulty.DIFFICULT,
                30,
                true,
                true,
                false,
                ingredientsr7,
                "Egy keverőtálban, konyhai robotgép segítségével minden alapanyagot összegyúrunk, majd kis golyókat " +
                        "formálunk a masszából. Sütőpapírral bélelt tepsire rakosgatjuk őket, majd villávalkissé " +
                        "lenyomjuk - ezzel mintegy mintázzuk is a felületüket. De ellapíthatjuk atenyererünkkelis, " +
                        "majd 3-3 csokicseppet vagy apróra vágott étcsokidarabot belenyomhatunka tésztába. 200 fokra " +
                        "előmelegített sütőben max. 10 perc alatt készre sütjük a kekszecskéinket!",
                "JPG",
                "pudingos_kekszek",
                photosToArray("pudingos_kekszek.jpg")
        );

        ingredientsr7.addAll(Arrays.asList(
                new Ingredient("margarin", 25, EnumUnit.DKG, r7),
                new Ingredient("porcukor", 10, EnumUnit.DKG, r7),
                new Ingredient("vaníliás cukor", 1, EnumUnit.TBS, r7),
                new Ingredient("finomliszt", 25, EnumUnit.DKG, r7),
                new Ingredient("pudingpor", 2, EnumUnit.PKG, r7),
                new Ingredient("csokicsepp", 0, EnumUnit.PCS, r7)
        ));

        repo.save(r7);

//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r8 = new Recipe("EGYSZERŰ BANÁNOS MUFFIN",
        EnumDifficulty.NAGYON_NEHEZ, 45, false, true, false,
        "\n2db banán \n15dkg liszt \n1csom sütőpor \n10dkg cukor \n1csom vaníliás cukor \n1db tojás \n0.8dl olaj \nvaj \nbanánkarikák",
        "A meghámozott banánokat összetörjük egy villával. Átszitáljuk egy tálba a lisztet és a sütőport, majd hozzáadjuk
        a cukrot, a vaníliás cukrot, a tojást, az olajat és a banánt, majd alaposan össszedolgozzuk. Kivajazzuk a
        muffinsütőt, majd a mélyedésekbe halmozzuk a tésztamasszát. 180 fokra előmelegített sütőben 20-30 percig sütjük,
        majd a formában hagyjuk kihűlni. A muffinokat banánkarikákkal díszítjük.\n");*/

        List<Ingredient> ingredientsr8 = new ArrayList<>();

        Recipe r8 = new Recipe(
                "Egyszerű banános muffin",
                EnumDifficulty.VERY_DIFFICULT,
                45,
                false,
                true,
                false,
                ingredientsr8,
                "A meghámozott banánokat összetörjük egy villával. Átszitáljuk egy tálba a lisztet és a sütőport, " +
                        "majd hozzáadjuk a cukrot, a vaníliás cukrot, a tojást, az olajat és a banánt, majd alaposan " +
                        "össszedolgozzuk. Kivajazzuk a muffinsütőt, majd a mélyedésekbe halmozzuk a tésztamasszát. 180 " +
                        "fokra előmelegített sütőben 20-30 percig sütjük, majd a formában hagyjuk kihűlni. A muffinokat " +
                        "banánkarikákkal díszítjük.",
                "JPG",
                "bananosmuffin",
                photosToArray("bananosmuffin.jpg")
        );

        ingredientsr8.addAll(Arrays.asList(
                new Ingredient("banán", 2, EnumUnit.PCS, r8),
                new Ingredient("liszt", 15, EnumUnit.DKG, r8),
                new Ingredient("sütőpor", 1, EnumUnit.PKG, r8),
                new Ingredient("cukor", 10, EnumUnit.DKG, r8),
                new Ingredient("vaníliás cukor", 1, EnumUnit.PKG, r8),
                new Ingredient("tojás", 1, EnumUnit.PCS, r8),
                new Ingredient("olaj", 0.8, EnumUnit.L, r8),
                new Ingredient("vaj", 0, EnumUnit.PCS, r8),
                new Ingredient("banánkarikák", 0, EnumUnit.PCS, r8)
        ));

        repo.save(r8);

//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r9 = new Recipe("EGYSZERŰ MEGGYES PALACSINTA",
        EnumDifficulty.NORMALIS, 35, false, true, false,
        "\n3db tojás \n15dkg cukor\n \n50dkg liszt \nszénsavas ásványvíz \nmeggylekvár",
        "A tojásokat feltörjük, egy tálba öntjük, majd a cukorral habosra kavarjuk, végül hozzáadjuk a lisztet.
        Ezeket összedolgozzuk és ásványvízzel lazítjuk a tésztát. A palacsintatészta sűrűségét egyénileg határozzuk meg.
        Bevonatos serpenyőben egyenként kisütjük a palacsintákat, és hűlni hagyjuk. A palacsintákat meggylekvárral
        töltjük meg, és háromszög alakúra hajtogatjuk.");*/

        List<Ingredient> ingredientsr9 = new ArrayList<>();

        Recipe r9 = new Recipe(
                "Egyszerű meggyes palacsinta",
                EnumDifficulty.NORMAL,
                35,
                false,
                true,
                false,
                ingredientsr9,
                "A tojásokat feltörjük, egy tálba öntjük, majd a cukorral habosra kavarjuk, végül hozzáadjuk " +
                        "a lisztet. Ezeket összedolgozzuk és ásványvízzel lazítjuk a tésztát. A palacsintatészta " +
                        "sűrűségét egyénileg határozzuk meg. Bevonatos serpenyőben egyenként kisütjük a palacsintákat, " +
                        "és hűlni hagyjuk. A palacsintákat meggylekvárral töltjük meg, és háromszög alakúra hajtogatjuk.",
                "JPG",
                "meggyes_palacsinta",
                photosToArray("meggyes_palacsinta.jpg")
        );

        ingredientsr9.addAll(Arrays.asList(
                new Ingredient("tojás", 3, EnumUnit.PCS, r9),
                new Ingredient("cukor", 15, EnumUnit.DKG, r9),
                new Ingredient("liszt", 50, EnumUnit.DKG, r9),
                new Ingredient("szénsavas ásványvíz", 0, EnumUnit.PCS, r9),
                new Ingredient("meggylekvár", 0, EnumUnit.PCS, r9)
        ));

        repo.save(r9);

//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r10 = new Recipe("TÍZPERCES SÜTŐPOROS FÁNK",
        EnumDifficulty.KONNYU, 10, false, true, false,
        "\n25dkg liszt \n1csip só \n0.5csom sütőpor \n1db tojás \n3ek porcukor \n1csom vaníliás cukor \n2dl tejföl \n6dl olaj",
        "A lisztet egy tálba szitáljuk, majd elkeverjük benne a sót és a sütőport. Egy másik tálban a tojást simára
        keverjük a porcukorral, a vaníliás cukorral és a tejföllel. Hozzáadjuk a sütőporos lisztet, és jól összedolgozzuk.
        Lisztezett felületen 0,5 cm vastagra nyújtjuk a tésztát, majd egy közepes átmérőjű pogácsaszaggatóval kiszaggatjuk.
         Bő, forró olajban, a közepestől egy kicsit kisebb lángon a fánkok mindkét oldalát szép pirosra sütjük. Konyhai
         papírtörlőn lecsepegtetjük a felesleges olajat. Porcukorral meghintve, sárgabaracklekvárral kínáljuk.");*/

        List<Ingredient> ingredientsr10 = new ArrayList<>();

        Recipe r10 = new Recipe(
                "Tízperces sütőporos fánk",
                EnumDifficulty.EASY,
                10,
                false,
                true,
                false,
                ingredientsr10,
                "A lisztet egy tálba szitáljuk, majd elkeverjük benne a sót és a sütőport. Egy másik tálban a tojást " +
                        "simára keverjük a porcukorral, a vaníliás cukorral és a tejföllel. Hozzáadjuk a sütőporos lisztet, " +
                        "és jól összedolgozzuk. Lisztezett felületen 0,5 cm vastagra nyújtjuk a tésztát, majd egy közepes " +
                        "átmérőjű pogácsaszaggatóval kiszaggatjuk. Bő, forró olajban, a közepestől egy kicsit kisebb lángon " +
                        "a fánkok mindkét oldalát szép pirosra sütjük. Konyhai papírtörlőn lecsepegtetjük a felesleges olajat. " +
                        "Porcukorral meghintve, sárgabaracklekvárral kínáljuk.",
                "JPG",
                "tizperces_sutoporos_fank",
                photosToArray("tizperces_sutoporos_fank.jpg")
        );

        ingredientsr10.addAll(Arrays.asList(
                new Ingredient("liszt", 25, EnumUnit.DKG, r10),
                new Ingredient("só", 1, EnumUnit.PINCH, r10),
                new Ingredient("sütőpor", 0.5, EnumUnit.PKG, r10),
                new Ingredient("tojás", 1, EnumUnit.PCS, r10),
                new Ingredient("porcukor", 3, EnumUnit.TBS, r10),
                new Ingredient("vaníliás cukor", 1, EnumUnit.PKG, r10),
                new Ingredient("tejföl", 0.2, EnumUnit.L, r10),
                new Ingredient("olaj", 0.6, EnumUnit.L, r10)
        ));

        repo.save(r10);

//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r11 = new Recipe("KLASSZIKUS TÚRÓGOMBÓC FAHÉJAS TEJFÖLLEL",
        EnumDifficulty.NEHEZ, 45, false, false, false,
        "\n10-15 db gombóchoz: \n50dkg túró \n2db tojás \n10dkg búzadara \n1csom vaníliáscukor \n2ek kristálycukor
        \n1db citrom reszelt héja \n1csip só \n10dkg zsemlemorzsa \n2ek porcukor\n2ek étolaj",
        "A túrót áttörjük, például egy krumplinyomóval. Összekeverjük a tojásokkal és a búzadarával, a cukorral, a
        citrom reszelt héjával, és a csipet sóval, majd letakarva minimum 1 órára tegyük hűtőbe – hogy a dara jól
        megszívja magát. Ezután nedves kézzel diónyi gombócokat formázunk a masszából, és enyhén sós, forró, gyöngyöző
        vízben kifőzzük – amint feljönnek a gombócok a víz tetejére, onnan számítva további 5 percig főzzük őket. Kevés
        olajon, alacsony lángon megpirítjuk a zsemlemorzsát, vigyázzunk mert könnyen túlbarnul. Két evőkanál porcukrot
        hozzákeverünk, és a kifőtt gombócokat ebbe az édes morzsába forgatjuk bele. A túrógombócot porcukorral megszórva,
        édes, fahéjas tejföllel, langyosan tálaljuk.");*/

        List<Ingredient> ingredientsr11 = new ArrayList<>();

        Recipe r11 = new Recipe(
                "Klasszikus túrógombóc fahéjas tejföllel",
                EnumDifficulty.DIFFICULT,
                45,
                false,
                false,
                false,
                ingredientsr11,
                "A túrót áttörjük, például egy krumplinyomóval. Összekeverjük a tojásokkal és a búzadarával, " +
                        "a cukorral, a citrom reszelt héjával, és a csipet sóval, majd letakarva minimum 1 órára tegyük" +
                        " hűtőbe – hogy a dara jól megszívja magát. Ezután nedves kézzel diónyi gombócokat formázunk a " +
                        "masszából, és enyhén sós, forró, gyöngyöző vízben kifőzzük – amint feljönnek a gombócok a víz " +
                        "tetejére, onnan számítva további 5 percig főzzük őket. Kevés olajon, alacsony lángon megpirítjuk" +
                        " a zsemlemorzsát, vigyázzunk mert könnyen túlbarnul. Két evőkanál porcukrot hozzákeverünk, és " +
                        "a kifőtt gombócokat ebbe az édes morzsába forgatjuk bele. A túrógombócot porcukorral megszórva," +
                        " édes, fahéjas tejföllel, langyosan tálaljuk." ,
                "JPG",
                "turogomboc",
                photosToArray("turogomboc.jpg")
        );

        ingredientsr11.addAll(Arrays.asList(
                new Ingredient("túró", 50, EnumUnit.DKG, r11),
                new Ingredient("tojás", 2, EnumUnit.PCS, r11),
                new Ingredient("búzadara", 10, EnumUnit.DKG, r11),
                new Ingredient("vaníliáscukor", 1, EnumUnit.PKG, r11),
                new Ingredient("kristálycukor", 2, EnumUnit.TBS, r11),
                new Ingredient("citrom reszelt héja", 1, EnumUnit.PCS, r11),
                new Ingredient("só", 1, EnumUnit.PINCH, r11),
                new Ingredient("zsemlemorzsa", 10, EnumUnit.DKG, r11),
                new Ingredient("porcukor", 2, EnumUnit.TBS, r11),
                new Ingredient("étolaj", 2, EnumUnit.TBS, r11)
        ));

        repo.save(r11);

//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r12 = new Recipe("TEJES PITE",
         EnumDifficulty.NORMALIS, 50, false, false, false,
         "\n4db tojás \n15dkg cukor \ncsip só \n1csom vaníliás cukor\n \n30dkg liszt \n1l tej
         \nvaj (a tepsi kikenéséhez) \nsárgabaracklekvár",
         "A tejes pite elkészítéséhez az egész tojásokat a cukorral, a sóval és a vaníliás cukorral kikeverjük,
         majd hozzáadjuk a lisztet és a tejet is, de csak apránként. Egynemű tésztává keverjük. Egy nagyobb, magas falú
         tepsit alaposan kikenünk vajjal, és beleöntjük a kevert tésztát. Előmelegített sütőben először magas hőmérsékleten
          (190 fokon) 10 percig, aztán alacsonyabb hőmérsékleten (170 fokon) 40 perc alatt szép pirosra sütjük.
          A süteményt négyzet alakúra vágjuk, és baracklekvárral tálaljuk.");*/

        List<Ingredient> ingredientsr12 = new ArrayList<>();

        Recipe r12 = new Recipe(
                "Tejes pite",
                EnumDifficulty.NORMAL,
                50,
                false,
                false,
                false,
                ingredientsr12,
                "A tejes pite elkészítéséhez az egész tojásokat a cukorral, a sóval és a vaníliás cukorral kikeverjük, " +
                        "majd hozzáadjuk a lisztet és a tejet is, de csak apránként. Egynemű tésztává keverjük. Egy " +
                        "nagyobb, magas falú tepsit alaposan kikenünk vajjal, és beleöntjük a kevert tésztát. " +
                        "Előmelegített sütőben először magas hőmérsékleten (190 fokon) 10 percig, aztán alacsonyabb " +
                        "hőmérsékleten (170 fokon) 40 perc alatt szép pirosra sütjük. A süteményt négyzet alakúra " +
                        "vágjuk, és baracklekvárral tálaljuk." ,
                "JPG",
                "tejespite",
                photosToArray("tejespite.jpg")
        );

        ingredientsr12.addAll(Arrays.asList(
                new Ingredient("tojás", 4, EnumUnit.PCS, r12),
                new Ingredient("cukor", 15, EnumUnit.DKG, r12),
                new Ingredient("só", 1, EnumUnit.PINCH, r12),
                new Ingredient("vaníliás cukor", 1, EnumUnit.PKG, r12),
                new Ingredient("liszt", 30, EnumUnit.DKG, r12),
                new Ingredient("tej", 1, EnumUnit.L, r12),
                new Ingredient("vaj", 0, EnumUnit.PCS, r12),
                new Ingredient("sárgabaracklekvár", 0, EnumUnit.PCS, r12)
        ));

        repo.save(r12);


//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r13 = new Recipe("TÚRÓS ZSEMLE, AHOGY A NAGYMAMÁM KÉSZÍTI",
         EnumDifficulty.NEHEZ, 35, false, false, false,
         "\n10db zsemle \n6dl tej \n5dkg vaj \n3ek cukor \n2.5dl tejföl \n0.5kg túró \n1db tojás \n1zacskó vaníliás cukor
         \n1db citrom \n4ek porcukor \n2ek tejfö \n2marék mazsola \n1csip só",
         "A töltelék hozzávalóit egy tálban simára keverjük. A zsemléket kettévágjuk, a belsejüket kikaparjuk, hogy
         kb. 1 cm vastag héj maradjon. A tejet langyosra melegítjük, és hozzáadjuk a megolvasztott vajat és a cukrot.
         A zsemlék belét leöntjük 1-2 dl vajas tejjel, kicsit kinyomkodjuk és a töltelékhez adjuk, majd ezzel is
         kikeverjük. Kivajazunk egy tűzálló tálat. A zsemléket megmártjuk a vajas tejben, és egymás mellé állítjuk a
         jénaiban. Elosztjuk bennük a tölteléket, meglocsoljuk a maradék vajas tejjel, majd mindegyik tetejére még
         néhány kanál tejfölt teszünk. 180 fokos sütőbe toljuk, és kb. 30 perc alatt készre sütjük (addig, amíg a
         zsemlék széle és a tejföl is itt-ott megpirul).");*/

        List<Ingredient> ingredientsr13 = new ArrayList<>();

        Recipe r13 = new Recipe(
                "Túrós zsemle, ahogy a nagymamám készíti",
                EnumDifficulty.DIFFICULT,
                35,
                false,
                false,
                false,
                ingredientsr13, /* "\n50dkg darált keksz \n50dkg porcukor \n6ek kakaópor \n6ek rum \n1db tojássárgája \n5ek tej \n20dkg vaj \n1tasak vaníliás cukor \n20dkg kókuszreszelék", */
                "A töltelék hozzávalóit egy tálban simára keverjük. A zsemléket kettévágjuk, a belsejüket kikaparjuk, " +
                        "hogy kb. 1 cm vastag héj maradjon. A tejet langyosra melegítjük, és hozzáadjuk a megolvasztott " +
                        "vajat és a cukrot. A zsemlék belét leöntjük 1-2 dl vajas tejjel, kicsit kinyomkodjuk és a " +
                        "töltelékhez adjuk, majd ezzel is kikeverjük. Kivajazunk egy tűzálló tálat. A zsemléket " +
                        "megmártjuk a vajas tejben, és egymás mellé állítjuk a jénaiban. Elosztjuk bennük a tölteléket, " +
                        "meglocsoljuk a maradék vajas tejjel, majd mindegyik tetejére még néhány kanál tejfölt teszünk. " +
                        "180 fokos sütőbe toljuk, és kb. 30 perc alatt készre sütjük (addig, amíg a zsemlék széle és " +
                        "a tejföl is itt-ott megpirul)." ,
                "JPEG",
                "turoval-toltott-zsemle",
                photosToArray("turoval-toltott-zsemle.jpeg")
        );

        ingredientsr13.addAll(Arrays.asList(
                new Ingredient("zsemle", 10, EnumUnit.PCS, r13),
                new Ingredient("tej", 0.6, EnumUnit.L, r13),
                new Ingredient("vaj", 5, EnumUnit.DKG, r13),
                new Ingredient("cukor", 3, EnumUnit.TBS, r13),
                new Ingredient("tejföl", 0.25, EnumUnit.L, r13),
                new Ingredient("túró", 0.5, EnumUnit.KG, r13),
                new Ingredient("tojás", 1, EnumUnit.PCS, r13),
                new Ingredient("vaníliás cukor", 1, EnumUnit.PKG, r13),
                new Ingredient("citrom", 1, EnumUnit.PCS, r13),
                new Ingredient("porcukor", 4, EnumUnit.TBS, r13),
                new Ingredient("tejföl", 2, EnumUnit.TBS, r13),
                new Ingredient("mazsola", 2, EnumUnit.CUP, r13),
                new Ingredient("só", 1, EnumUnit.PINCH, r13)
        ));

        repo.save(r13);


//----------------------------------------------------------------------------------------------------------------------

        /*Recipe r14 = new Recipe("Macskapörkölt",
        EnumDifficulty.LEHETETLEN, 999999999, false, false, true,
        "\nkiscica \nsó \nbors",
        "Végy egy kiscicát. Ízesitsd sóval, borssal, majd sütőben 180 fokon egy órán át süsd.\n " +
                "Ha kész, ébredj fel! \n" +
                "A cicák túl aranyosak, így nem lenne szíved megenni! :) \n");*/

        List<Ingredient> ingredientsr14 = new ArrayList<>();

        Recipe r14 = new Recipe(
                "Kis cica",
                EnumDifficulty.IMPOSSIBLE,
                999999999,
                true,
                true,
                true,
                ingredientsr14,
                "Végy egy kiscicát. Nyúzd meg és zsigereld ki, (a bundáját rakd félre mamusznak), irdald be a " +
                        "bőrét, rakd az előkészített tésztára, öntsd le forró cukormázzal, díszítsd csokimázzal, " +
                        "majd előmelegített sütőben 180 fokon egy órán át süsd. Ha kész, ébredj fel! " +
                        "A cicák túl aranyosak, így nem lenne (?) szíved megenni! :) " ,
                "JPG",
                "kiscica",
                photosToArray("kiscica.jpg")


        );

        ingredientsr14.addAll(Arrays.asList(
                new Ingredient("kiscica", 3, EnumUnit.KG, r14),
                new Ingredient("só", 1, EnumUnit.PINCH, r14),
                new Ingredient("bors", 2, EnumUnit.PINCH, r14)
        ));

        repo.save(r14);

        AppUser appUser1 = new AppUser("Barát", "Tak", "TakBarát");
        appUser1.setPhotoName("JPG");
        appUser1.setPhotoType("barat");
        appUser1.setPhotoData(photosToArray("barat.jpg"));
        appUser1.setPassword("asdf");
        appUserService.saveUser(appUser1);
        AppUser appUser2 = new AppUser("Robi", "Adakozó", "AdakozóRobi");
        appUser2.setPhotoName("JPG");
        appUser2.setPhotoType("robi");
        appUser2.setPhotoData(photosToArray("robi.jpg"));
        appUser2.setPassword("asdf");
        appUserService.saveUser(appUser2);
        AppUser appUser3 = new AppUser("Zsolt", "Kocsor", "Áldott");
        appUser3.setPhotoName("JPG");
        appUser3.setPhotoType("zsolt");
        appUser3.setPhotoData(photosToArray("zsolt.jpg"));
        appUser3.setPassword("asdf");
        appUserService.saveUser(appUser3);
        AppUser appUser4 = new AppUser("Éva", "Varga", "Gidácska");
        appUser4.setPhotoName("JPG");
        appUser4.setPhotoType("eva");
        appUser4.setPhotoData(photosToArray("eva.jpg"));
        appUser4.setPassword("asdf");
        appUserService.saveUser(appUser4);
        List<AppUser> appUsers = new ArrayList<>();
        appUsers.add(appUser1);
        appUsers.add(appUser2);
        appUsers.add(appUser3);
        appUsers.add(appUser4);

        AppUser appUser = new AppUser("Tihamér", "Teszt", "TeszTiha",
                "tetszti@teszt.hu", "tesztkód");
         List<Recipe> ownRecipes = new ArrayList<>();
         List<Recipe> favouriteRecipes = new ArrayList<>();

         ownRecipes.add(r1);
         ownRecipes.add(r10);
         ownRecipes.add(r7);
         ownRecipes.add(r11);
         ownRecipes.add(r13);

         favouriteRecipes.add(recipeService.findById(60L));
         favouriteRecipes.add(recipeService.findById(70L));
         favouriteRecipes.add(recipeService.findById(76L));
         favouriteRecipes.add(recipeService.findById(85L));
         favouriteRecipes.add(recipeService.findById(96L));

         appUser.setFriends(appUsers);
         appUser.setOwnRecipes(ownRecipes);
         appUser.setFavouriteRecipes(favouriteRecipes);
         appUser.setPhotoName("JPG");
         appUser.setPhotoType("cakeman");
         appUser.setPhotoData(photosToArray("cakeman.jpg"));

        appUserService.saveUser(appUser);

//----------------------------------------------------------------------------------------------------------------------

        //ingredients.forEach(ingredient -> ingredient.setRecipe(r1)); // ez meg mi a fasz ?
    }


    /**
     * Ria játszott egyet. :)
     *
     * @param keyword the keyword to search
     * @return the MAGIC!
     */
    public List<Recipe> ria(String keyword) {
        return ((List<Recipe>) repo.findAll())
                .stream().filter(recipe ->
                        recipe.getIngredients().stream().anyMatch(ingredient ->
                                ingredient.getName().contains(keyword))
                )
                .toList();
    }




    public static byte[] photosToArray(String ImageName) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources/static/photos/" + ImageName));
        ByteArrayOutputStream bos =new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", bos);
        return bos.toByteArray();
    }
   /* public List<Ingredient> findByIngredients(String  keyWord, Recipe recipe){
        List<Ingredient> result = new ArrayList<>();

        for(Ingredient search : ingredients.values()){
            if(recipe.getIngredients().contains(keyWord)){
                result.add(search);
            }
        }
        return result;
    }*/

    /*public List<Recipe> getByIngredients() {

        return repo.findByIngredients(new ArrayList<>());
    }*/


/* public static byte[] extractBytes(String ImageName) throws IOException {
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources/static/photos/" + ImageName));

        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();


        return (data.getData());

    }

    */
}
