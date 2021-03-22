import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType3Font;

public class Main {
    public static List<Song> songs;
    public static void main(String args[]) throws IOException {
        PDDocument document = new PDDocument();
        initializeSongs();
        int i = 0;
        for(Song s : songs)
        {
            document.addPage(new PDPage());
            PDPageContentStream pdcs = new PDPageContentStream(document, document.getPage(i));
            pdcs.beginText();
            pdcs.setFont(PDType1Font.TIMES_ROMAN, 13);
            pdcs.setLeading(14.5f);
            pdcs.newLineAtOffset(25, 725);
            pdcs.showText(s.name);
            pdcs.newLine();
            pdcs.showText(s.interpret);
            pdcs.newLine();
            pdcs.showText(s.length +" min");
            pdcs.newLine();
            pdcs.setFont(PDType1Font.TIMES_ROMAN, 10);
            String[] ss = s.lyrics.split("\n");
            for(int j = 0; j< ss.length;++j)
            {
                pdcs.showText(ss[j]);
                pdcs.newLine();
            }
            pdcs.endText();
            i++;
            pdcs.close();
        }
        document.save("out.pdf");

        System.out.println("PDF created");
        document.close();
    }

    public static void initializeSongs(){
        songs = new ArrayList<>();
        Song s = new Song("Fantasy", 4.22, "Koi wa purizumu no fantasy\n" +
                "Dakara umare kawareru hazu\n" +
                "Kako wa anata ni tsudzuku kaiten tobira\n" +
                "\n" +
                "Yoru wa purizumu no fantasy\n" +
                "Futari nanairo ni terashite\n" +
                "Ima wa atarashii ude no naka de dancing\n" +
                "\n" +
                "Hanayaida tokai o nukedashi\n" +
                "Kuruma wa suberu no\n" +
                "Hoshi o chiribameta paati raito\n" +
                "Futari no yoru ga hajimaru no\n" +
                "\n" +
                "Ano toki koi o nakushite kara\n" +
                "Fuyu no kirameki o\n" +
                "Wasurete ita no ni\n" +
                "\n" +
                "Koi wa purizumu no fantasy\n" +
                "Dakara umare kawareru hazu\n" +
                "Kako wa anata ni tsudzuku kaiten tobira\n" +
                "\n" +
                "Yoru wa purizumu no fantasy\n" +
                "Futari nanairo ni terashite\n" +
                "Ima wa atarashii ude no naka de dancing\n" +
                "\n" +
                "Supankoru no yokaze wa bay city\n" +
                "Kegawa o nuidara\n" +
                "Kurisumasu tsurii kazaru mise de\n" +
                "Asamade odori akashitai\n" +
                "\n" +
                "Mou dare mo aisu koto wa nai to\n" +
                "Shinji teta yesterday\n" +
                "Ima wa chigau no\n" +
                "\n" +
                "Koi wa purizumu no fantasy\n" +
                "Dakara umare kawareru hazu\n" +
                "Kako wa anata ni tsudzuku kaiten tobira\n" +
                "\n" +
                "Yoru wa purizumu no fantasy\n" +
                "Futari nanairo ni terashite\n" +
                "Ima wa atarashii ude no naka de dancing", "Meiko Nakahara");
        songs.add(s);
        s = new Song("Fate of a Coward", 2.56, "My mind is seeping\n" +
                "Into the darkness\n" +
                "My pulse is growing\n" +
                "Weaker by the moment\n" +
                "From fight each battle\n" +
                "In a war that can't be won\n" +
                "I might as well end it\n" +
                "With a single shot to my skull\n" +
                "Don't tell me\n" +
                "Don't tell me\n" +
                "That the worst is yet to come\n" +
                "Don't tell me\n" +
                "Don't tell me\n" +
                "There's no justice after all\n" +
                "That fate mapped it out so perfectly\n" +
                "To fight the regards\n" +
                "Of the coward\n" +
                "The years I spent scrubbing the blood I never shed\n" +
                "Encased in fear bound to repetition\n" +
                "You can't fool me no not anymore\n" +
                "I see the bastards grin\n" +
                "Through your confession\n" +
                "Don't tell me\n" +
                "Don't tell me\n" +
                "That the worst is yet to come\n" +
                "Don't tell me\n" +
                "Don't tell me\n" +
                "There's no justice after all\n" +
                "That fate mapped it out so perfectly\n" +
                "To fight the regards\n" +
                "Of the coward\n" +
                "I wanna see you cower\n" +
                "In the screaming echoes of your past\n" +
                "I wanna see you cower\n" +
                "And burn this cycle into dust\n" +
                "I wanna see you cower\n" +
                "And self destruct\n" +
                "And if that's not enough\n" +
                "I'll haunt your dreams like you haunted mine\n" +
                "Claw at the sockets of your deceiving eyes\n" +
                "I'll make you rue the day\n" +
                "You ever laid your hands on me\n" +
                "Don't tell me\n" +
                "Don't tell me\n" +
                "That the worst is yet to come\n" +
                "Don't tell me\n" +
                "Don't tell me\n" +
                "There's no justice after all\n" +
                "That fate mapped it out so perfectly\n" +
                "To fight the regards\n" +
                "Of the coward\n" +
                "Don't tell me\n" +
                "Don't tell me\n" +
                "That the worst is yet to come\n" +
                "Don't tell me\n" +
                "Don't tell me\n" +
                "There's no justice after all\n" +
                "That fate mapped it out so perfectly\n" +
                "To fight the regards\n" +
                "Of those cowards", "DaysNDaze");
        songs.add(s);
        s = new Song("Never Gonna Give You Up", 3.33, "We're no strangers to love\n" +
                "You know the rules and so do I\n" +
                "A full commitment's what I'm thinking of\n" +
                "You wouldn't get this from any other guy\n" +
                "I just wanna tell you how I'm feeling\n" +
                "Gotta make you understand\n" +
                "Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna run around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n" +
                "We've known each other for so long\n" +
                "Your heart's been aching but you're too shy to say it\n" +
                "Inside we both know what's been going on\n" +
                "We know the game and we're gonna play it\n" +
                "And if you ask me how I'm feeling\n" +
                "Don't tell me you're too blind to see\n" +
                "Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna run around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n" +
                "Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna run around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n" +
                "Never gonna give, never gonna give\n" +
                "(Give you up)\n" +
                "We've known each other for so long\n" +
                "Your heart's been aching but you're too shy to say it\n" +
                "Inside we both know what's been going on\n" +
                "We know the game and we're gonna play it\n" +
                "I just wanna tell you how I'm feeling\n" +
                "Gotta make you understand\n" +
                "Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna run around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n" +
                "Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna run around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n" +
                "Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna run around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye", "Rick Astley");
        songs.add(s);
    }
}
