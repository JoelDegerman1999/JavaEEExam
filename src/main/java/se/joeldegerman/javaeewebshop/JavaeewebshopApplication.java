package se.joeldegerman.javaeewebshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import se.joeldegerman.javaeewebshop.models.entity.Category;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.repositories.CategoryRepository;
import se.joeldegerman.javaeewebshop.repositories.ProductRepository;

@SpringBootApplication
@ConfigurationPropertiesScan("se.joeldegerman.javaeewebshop.security.jwt")
public class JavaeewebshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaeewebshopApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CategoryRepository c, ProductRepository p) {
        return args -> {
            if (p.findAll().size() == 0) {
                Category laptop = c.save(new Category("Laptop"));
                Category desktop = c.save(new Category("Desktop"));
                Category mobile = c.save(new Category("Mobile"));
                Category console = c.save(new Category("Console"));
                Category monitor = c.save(new Category("Monitor"));
                p.save(new Product("Acer Aspire 3 - 14\"", 4490, "https://inetimg.se/img/688x386/1964941_0.jpg", "Den här datorn är perfekt för dig som vill ha en snabb vardagsdator. Du får oerhört mycket prestanda jämfört mot mycket annat i prisklassen. En AMD Ryzen-processor i kombination med 4GB internminne och 128GB SSD PCIe ger dig bra med kraft för de flesta ändamålen.", laptop));
                p.save(new Product("ASUS ZenBook 14", 12990, "https://inetimg.se/img/688x386/1965025_0.jpg", "Asus Zenbook 14 är den bärbara datorn som fungerar lika bra oavsett vilken livsstil du har. Med upp till 13 timmars batteritid kan du jobba hela dagen och streama serier när du kommer hem utan att behöva ladda datorn. Med oerhört tunna skärmramar och 92% skärmyta får du en 14\"-skärm i ett 13\"-chassi. Med ErgoLift får du dessutom en bekvämare skrivvinkel.", laptop));
                p.save(new Product("Huawei MateBook 14", 11990, "https://inetimg2.se/img/688x386/1965287_0.jpg", "Huawei MateBook 14 erbjuder den senaste prestandan och en attraktiv design. Den har en högupplöst 3:2 skärm med 100% sRGB vilket ger en exakt och livfull färgåtergivning. Med 10: e generationens Intel core i5 processor och GeForce MX350 klarar den både videoredigering och enklare spel. Datorns eleganta och ultralätta aluminiumchassi är 1,59 cm tunt och väger endast 1,49 kg. Denna bärbara dator är din perfekta följeslagare oavsett om du reser, är i skolan eller bara slappar hemma i soffan.", laptop));
                p.save(new Product("Huawei MateBook D", 6990, "https://inetimg3.se/img/688x386/1964527_14.jpg", "Huawei MateBook D 14\" är byggd av finaste metall med mjukt rundade kanter som ger ett sofistikerat intryck. Med en vikt på endast 1,38 Kg och en tjocklek på 15,9 mm tillåter den dig att vara flexibel och passar både för nytta och nöje. Med fingeravtrycksläsaren i power-knappen både startar du och loggar in på din dator i ett enda steg. Datorn laddas dessutom via USB-C.", laptop));
                p.save(new Product("Taurus Gaming RTX 3070 - 5600X", 18499, "https://inetimg3.se/img/688x386/1511029_5.jpg", "En gamingdator med RTX 3070 och AMD Ryzen 5 5600X. Perfekt för dig som vill spela med hög FPS i tunga spel.", desktop));
                p.save(new Product("Apple IPhone 11 (128GB)", 7790, "https://inetimg3.se/img/688x386/1964108_1.jpg", "iPhone 11 är exakt rätt mängd av allt. Kraftfull, färgstark och ett absolut måste. Med en 6,1 tum Liquid Retina-skärm och verklighetstrogen färgåtergivning kommer du kunna uppleva bilder, videos och spel i en kvalitet av världsklass. Ett dubbelkamerasystem levererar bilder och videos som imponerar. Innovativa optiska funktioner som ultravidvinkel, Night Mode och porträttläge är några av anledningarna att du kommer älska iPhone 11. Och det nya A13 Bionic-chipet ger dig en enastående prestanda.", mobile));
                p.save(new Product("Apple IPhone 12 Pro (128GB) 5G", 12495, "https://inetimg3.se/img/688x386/1965725_1.jpg", "Nya iPhone 12 Pro har en fantastisk 6,1-tum Super Retina XDR-skärm och det kraftfullaste chippet någonsin, A14 Bionic. Med LiDAR-tekniken kan du skanna av alla typer av ytor, vilket revolutionerar funktioner som AR. Både vidvinkel- och ultravidvinkelkameran är nu utrustad med nattläge för ännu bättre bilder i svagt ljus. Du kan även att spela in video i Dolby Vision, vilket inte ens professionella videokameror klarar av. Ta enastående foton tillsammans med världsledande maskininlärning.", mobile));
                p.save(new Product("ASUS Zenfone 7 (128GB) 5G", 7990, "https://inetimg3.se/img/688x386/2000619_0.jpg", "64 MP Flip Camera trippelkamera fram och bak för både foto och video", mobile));
                p.save(new Product("OnePlus 8 Pro", 8999, "https://inetimg3.se/img/688x386/2000490_0.jpg", "OnePlus 8 Pro är kärlek vid första svajpen. Upptäck snabbheten i den omfattande 6,78” QHD+ displayen med en bilduppdateringsfrekvens på hela 120 Hz. De fyra kraftfulla kamerorna banar väg för din kreativitet och ger dig oändliga möjligheter. Välj hur du vill ladda med Warp Charge 30T och Warp Charge 30 Wireless. Med massiva 4510 mAh så har din telefon tillräckligt med kraft för att klara hela dagen och mer därtill. OnePlus 8 Pro sätter standarden för prestanda, tack vare Snapdragon 865 och 5G.", mobile));
                p.save(new Product("Microsoft Xbox Series X", 5695, "https://inetimg3.se/img/688x386/6335950_5.jpg", "Xbox Series X by Microsoft", console));
                p.save(new Product("Sony Playstation 5", 5999, "https://inetimg3.se/img/688x386/6609649_5.jpg", "PS5 -konsolen möjliggör det du aldrig hade förväntat dig i spelväg. Upplev blixtsnabb laddning med en ultrahög hastighet SSD, med stöd för haptisk feedback, adaptiva utlösare och 3D-ljud och en helt ny generation av otroliga spel.", console));
                p.save(new Product("Acer 24\" Gaming", 2349, "https://inetimg2.se/img/688x386/2216903_0.jpg", "EG240YP är en 24”-skärm som är perfekt för dig som tar gaming på allvar, och som inte tillåter att din utrustning håller dig tillbaka. Den har en skärmpanel på upp till 165 Hz, så att din reaktionsförmåga inte hindras av en långsam uppdateringshastighet. Den är dessutom utrustad med Freesync, så att du inte behöver oroa dig för tearing. Både DP- och HDMI-kabel medföljer och den går utmärkt att montera på vägg- eller bordsfäste med VESA.", monitor));
                p.save(new Product("Acer 38\" Predator", 14999, "https://inetimg3.se/img/688x386/2216313_0.jpg", "- Eliminera tearing med riktig G-Sync-modul.", monitor));
            }
        };
    }
}
