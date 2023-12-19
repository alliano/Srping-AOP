# Requirement
* Java
* Sring framework
* SpringBoot

# AOP
Aop atau singkatan dari Aspect Oriented Programming adalah pelengkap dari konsep OOP dalam membuat kode program.  
Pada konsep OOP inti modularitas nya adalah class, namun pada AOP inti dari modularitasnya terletak pada aspect.

Nah, AOP ini adalah sebuah proses yang memisahkan cross cutting concren dari bisnis logic atau
fungsionalistas utama dari sebuah services tujuanya adalah untuk meningkatkan modularitas
dari sebuah applikasi hal ini dilakukan dengan mendeklarasikan aspec yang nantinya aspec ini
akan melakukan alter alias mengubah beavior atau perilaku dari base code nya base code disini
merujuk pada service yang menagani suatu fungsionalitas nya.
caranya dengan meng applay sebuah advice kepada sebuah join point tertentu yang ditentukan
oleh poincut.

# Spring AOP
Spring framework memiliki 2 fitur AOP :
* impelemtasi sendiri mengguakan srping AOP
* menggunakan liberary AspectJ
Pada materi ini kita akan menggunakan AspectJ.

# AspectJ
AspectJ adalah libelary yang sangat populer di kalagan java developer untuk mengimplementasikan AOP, dan spring juga telah terinregasi dengan baik dan stabil dengan liberary AspectJ.  
https://www.eclips.org/aspectj  
  
Sebelum kita menggunakan spring AspectJ, kita harus menambahkan dependency nya terlebih dahulu  
``` xml
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

# Tampa menggunakan AOP
``` java
@Component @Slf4j
public class ApplicationService {
    
    public void hello() {
        log.info("Hello from ApplicationService");
    }

    public void hello2() {
        log.info("Hello 2 from ApplicationService");
    }
}
```

``` java
@SpringBootTest
class AopApplicationTests {

	private @Autowired ApplicationService applicationService;

	@Test
	public void testApplicationService() {
		this.applicationService.hello();
		this.applicationService.hello2();
	}

}
```

kode diatas merupakan kode tampa menggunakan AOP, pada class ApplicationService terdapat 2 method yang akan di eksekusi ketika applikasi pertama kali dijalankan di jalankan.  
  
Jika kita menggunakan AOP maka kode akan terlihat lebih modular.
# Enable AspectJ
Secara default Spring Framework tidak mengaktifkan fitur AOP secara aotomatis, melainkan kita harus mengaktifkanya secara manual.  
Untuk mengaktifkan fitur AOP dengan AspectJ, kita cukup menambahkan annotation @EnableAspectJAutoProxy pada main class

``` java
@EnableAspectJAutoProxy // mengkatifkan fitur AOP AspectJ
@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}
}
```

# Aspect
Seperti yang kita bahas sebelumnya inti dari modularitas AOP terletak pada Aspect, nah :v  
Untuk membuat aspect kita cukup membuat Bean class setelah itu kita tambahkan annoation @Aspect pada bean class tersebut.  
Maka spring secara otomatis akan membautkan object bean aspect dari class bean tersebut.

``` java
@Component @Aspect
public class LoggerAspect {
    
}
```

# Join Poin
Seteah membuat Aspect, agar aspect itu bisa digunakan ktia harus membuatkan sebuah JoinPoin.  
**JoinPoin** adalah titik lokasi program yang ingin di eksekusi.  
AspectJ sebenarnya mendukung banyak sekali JoinPoin, namun spring hanya mendukung Join Poin pada eksekusi method Bean.  

Contoh JoinPoin :
 * Mengeksekusi method hello pada ApplicationService
 * Mengeksekusi semua method pada ApplicationService
 * Mengeksekusi semua method yang memiliki annoation tertentu ApplicationService
 * Mengeksekusi method pada package service yang terjadi throwing Exception
 * dan sebagainya, masih banyak contoh-contoh JoinPoin

# Poin Cut
Setelah membuat Aspect dan JoinPoin, hal yang wajid dilakukan yaitu membuat Poincut.  
Poincut adalah kondisi yang digunakan untuk menentukan JoinPoin, dan ktika kodisi tersebut terpenuhi maka aspect akan mengeksekusi Advice.  
Untuk membuat Poincut, kita cukup berikan annotation @Poincut pada method

``` java
@Component @Aspect
public class LoggerAspect {
    
    @Pointcut(value = "ekpresi poincut")
    public void applicationSercviceHello() {}
}
```

# Poincut expression
Saat kita membuat Poincut maka kita harus menambahkan expression yang berisi kodisi untuk JoinPoint nya.  
Misalnya kita ingin Poincut untuk mengeeksekusi semua method yaang ada didalam ApplicationService, maka kita harus membuat kondisi tersebut dalam poincut expression.  
Sebenarnya AspectJ mendukung banyak sekali poincut expression, namun lagi-lagi hanya poincut expression yang berhubungan dengan method yang didukung oleh spring framework, diataranya yaitu : 

| Expression    |   Deskripsi
|---------------|------------------
| execution     | untuk mencocokan method yang nantinya dijadikan sebagai joinpoin nya
| within        | digunakan untuk membatasi kriteria joinpoin dengan parameter yang digunakna. penggunakan nya ini mirip dengan execution()
| this          | bean reference, maksudnya bean yang instance nya kita tentukan dan didalamnya memiliki method
| target        | Object dari instance dari tipe yang kita tentukan
| args          | argument method yang tipe yang telah ditentukan.

Misalnya kita ingin membuat poincut yang joinpoin nya semua method pada class ApplicationService, maka kita bisa membuat seperti ini :  
``` java
@Component @Aspect
public class LoggerAspect {
    // untuk joinpoin(ini merujuk ke ApplicationService) nya harus spring bean
    @Pointcut(value = "target(sprig.aop.aop.ApplicationService)")
    public void applicationSercviceHello() {}
}
```

Stelah itu agar poincut nya berfungsi, kita memerukan advice.  
Advice adalah aksi yang terjadi ketika terjadi eksekusi method pada poincut nya, dalam kasus kita kaliini merujuk pada eksekusi method yang terjadi pada ApplicationService.  
  
Ada beberapa jenis Advice 
| Advice            | Deskripsi 
|-------------------|-----------------
| @Before           | aspec method akan dieksekusi sebelum joinpoin dijalankan
| @After            | aspec method akan dieksekusi setelah joinpoin dijalankan
| @AfterReturning   | aspec akan dieksekusi setelah joinpoinnya melakukan return
| @AfterThrowing    | aspect akan dijalankan setelah joinpoinya menggalami throw exception
| @Around           | spect akan dieksekusi baik sebelum dan sesudah esekusi dari method joinpoin nya. Advice ini adalah advice yang paling powerful menurut saya karna dengan menggunakan advice in kita dapt menulis sebuah logic yang membuat aspec seolah olah membungkus dari method joinpoinnya ini seperti menggabungkan advice @After dan @Before secara bersamaan


``` java
@Slf4j
@Component @Aspect
public class LoggerAspect {
    
    @Pointcut(value = "target(sprig.aop.aop.ApplicationService)")
    public void applicationSercviceHello() {}

    /**
     * advice ini akan di eksekusi sebelum semua method pada ApplicationService di eksekusi 
     * */
    @Before(value = "applicationSercviceHello()")
    public void beforeApplicationService() {
        log.info("Execution before applicationService");
    }
}
```
Jika kita ingin melihat informasi detal dari advice kita(AplicationService), maka kita bisa menggunakan JoinPoin sebagai parameter
``` java
@Slf4j
@Component @Aspect
public class LoggerAspect {
    
    @Pointcut(value = "target(sprig.aop.aop.ApplicationService)")
    public void applicationSercviceHello() {}

    @Before(value = "applicationSercviceHello()")
    public void beforeApplicationService(JoinPoint joinPoint) {
        // classname
        String methodName = joinPoint.getSignature().getName();
        // class name
        String className = joinPoint.getTarget().getClass().getName();

        log.info("before "+className+"."+methodName+"()");
        log.info("Execution before applicationService");
    }
}
```

# Poincut Expression
Sebelumnya kita sudah mencoba menggunakan Poincut expression untuk target()  
setiap poincut memiliki format nya sendiri, dan poincut yang memiliki format paling fleksibel adalah execution.  

## Non Execution
Untuk poincut selain execution expresinya cukup sederhana, kita hanya menyebutkan nama target class nya saja dalam bentuk package.  
Target juga bisa menggunakan regex.  

## Contoh Expression
* within(com.aop.service.*) Artinya semua bean method yang ada di package service
* within(com.aop.service..*) Artinya semua method bean di package service dan sub package nya.  
* target(com.aop.UserService) Artinya semua method bean UserService
* args(java.lang.String, java.lang.String) Artinya semua method bean yang memiliki dua parameter String.  
* @target(org.springframework.transaction.Transaction) Artinya semua method yang memiliki annotation @Transaction
* bean(paymetService) Artinya method bean yang memiliki nama paymentService
* bean(*Service) semua method bean dengan nama akhir Service

## Execution
Untuk poincut execution, memiliki format yang sangat kompleks, berikut ini contoh formatnya :  
`execution(modiffier-pateren type-parameter.method-pateren(param-pateren) throws-pateren)`
untuk lebih deailnya bisa kunjungi disini https://eclipse.dev/aspectj/doc/released/progguide/semantics-pointcuts.html
  
Berikut ini contoh penggunakan Poincut expression()  
``` java
@Setter @Getter @Builder
public class UserRequest {
    
    private String firstName;

    private String lastName;

    private String email;
}
```

``` java
@Component @Slf4j
public class UserService {

    public void saveUser(UserRequest userRequest) {
        log.info("SAVING NEW USER");
    }
}
```

``` java
@Component @Slf4j @Aspect
public class LogerUserService {
    
    @Pointcut(value = "execution(public * sprig.aop.aop.UserService.*(sprig.aop.aop.helper.UserRequest))")
    public void logForUserService(){}

    @After(value = "logForUserService()")
    public void logUserService(JoinPoint joinPoint) {
        try {
            UserRequest userRequest = (UserRequest)joinPoint.getArgs()[0];
            log.info("firstName : "+userRequest.getFirstName()+" lastName : "+userRequest.getLastName()+" email : "+userRequest.getEmail());
        }
        catch (Throwable throwable) {
            throw throwable;
        }
        finally {
            log.info("done execute ...");
        }
    }
}
```

# Multiple Poincut
Saat kita menggunakan Poincut kita bisa menggunakan bebrapa poincut expression.  
Dan ini sangat bermanfaat jikalau kita ingin melakukan kombinasi bebrapa poincut sehingga lebih mudah digunakan.  
Misalnya ketika kita membuat poincut khusus untuk package service  
Dan kita membuat poincut khusus untuk bean dengan suffix Service.  
dan kita membuat poincut khusus untuk public method.  
Kita bisa menggabungkan beberapa poincut diatas menjadi poincut baru hasil penggabungan poincut diatas.  
kita bisa menggunakan tanda && untuk menggabungkan Poincut diatas.  
contoh : 
``` java
@Component @Slf4j
public class AccountService {
    
    public void addAccount() {
        log.info("add acound service.....");
    }
}
```

``` java
@Component @Slf4j
public class AuthenticationService {

    public void authenticate(String username, String password) {
        log.info("authenticate.......");
    }
}
```

``` java
@Component @Slf4j
public class PaymentService {
    
    public void pay() {
        log.info("Transaction ......");
    }
}
```

``` java
@Component @Aspect @Slf4j
public class MultiplePoincut {
    
    @Pointcut(value = "execution(* sprig.aop.aop.service.*.*(..))")
    public void poinctuServicePackage() {}

    @Pointcut(value = "bean(*Service)")
    public void poincutServiceBean(){}

    @Pointcut(value = "execution(public * *(..))")
    public void poincutPublicMethod() {}

    @Pointcut(value = "poinctuServicePackage() && poincutServiceBean() && poincutPublicMethod()")
    public void publiucMethodService() {}

    @Before(value = "publiucMethodService()")
    public void logAllService() {
        log.info("Log all for public method service .................");
    }
}
```

``` java
@SpringBootTest
class AopApplicationTests {

	private @Autowired AccountService accountService;

	private @Autowired AuthenticationService authenticationService;

	private @Autowired PaymentService paymentService;

    @Test
	public void testMultiplePoincut() {
		this.accountService.addAccount();
		this.authenticationService.authenticate("Ahfung", "ahfung zheng");
		this.paymentService.pay();
	}
}
```

# Parsing Parameter
Saat kita ingin mengambil value dari argument method, maka kita akan menggunakan `joinpoin.getArgs()`.  
Namun return value nya berupa Object of array `Object[]`, jika kita ingin langsung mengabil argument methodnya tampa melakukan konversi manual maka kita bisa memanfaatkan poincut expression args().  

Cara melakukanya cukup sederhana, kita cukup gunakan args(namaParameter)  
Catatan : nama nama Parameter pada args harus sama dengan parameter yang ada pada method.    

``` java
@Slf4j
@Configuration
public class Greeting {
    
    public void say(String greeting, String name) {
        log.info("greting....");
    }
}
```

``` java
@Configuration @Aspect @Slf4j
public class ParsingParam {

    @Pointcut(value = "execution(* sprig.aop.aop.Greeting.*(java.lang.String, java.lang.String))")
    public void poincutGreeting() {}

    // Disini kita tidak perlu menggunakan Joinpoint lagi
    @Before(value = "poincutGreeting() && args(greeting, name)")
    public void gretting(String greeting, String name) {
        log.info(greeting+" "+name);
    }
}
```

``` java
@SpringBootTest
class AopApplicationTests {

	private @Autowired Greeting greeting;

	@Test
	public void testParseParameter() {
		this.greeting.say("Hello", "Ahfung");
	}
}
```