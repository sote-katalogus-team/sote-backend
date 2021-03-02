package com.katalogus.project.service;

import com.katalogus.project.entity.*;
import com.katalogus.project.repository.*;
import com.katalogus.project.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Component
public class DBInitializer {

    @Autowired
    TurnusRepository turnusRepository;

    @Autowired
    EloadasRepository eloadasRepository;

    @Autowired
    GyakorlatRepository gyakorlatRepository;

    @Autowired
    KonzultacioRepository konzultacioRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        turnusRepository.save(Turnus.builder()
                .name("Turnus_angol")
                .consultation(100)
                .practice(100)
                .lecture(75)
                .year(2021)
                .combinedName("Turnus_angol/2021")
                .build());
        turnusRepository.save(Turnus.builder()
                .name("Turnus_magyar")
                .consultation(100)
                .practice(100)
                .lecture(75)
                .year(2021)
                .combinedName("Turnus_magyar/2021")
                .build());
        teacherRepository.save(Teacher.builder()
                .email("teacher1@teacher.com")
                .name("tanar1")
                .password(passwordEncoder.encode("password"))
                .roles(List.of(ApplicationUserRole.TEACHER))
                .build());
        teacherRepository.save(Teacher.builder()
                .email("teacher2@teacher.com")
                .name("tanar2")
                .password(passwordEncoder.encode("password"))
                .roles(List.of(ApplicationUserRole.TEACHER))
                .build());
        teacherRepository.save(Teacher.builder()
                .email("admin@admin.com")
                .name("admin")
                .password(passwordEncoder.encode("password"))
                .roles(List.of(ApplicationUserRole.TEACHER, ApplicationUserRole.ADMIN))
                .build());

        //Előadás generálás, és hozzáadás
        Eloadas bevezeto = Eloadas.builder()
                .date(new Date())
                .name("Bevezető")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(bevezeto);

        Eloadas gyermekgyogyaszat = Eloadas.builder()
                .date(new Date())
                .name("Gyermekgyógyászat")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(gyermekgyogyaszat);

        Eloadas surgossegiGyermekgyogyaszat = Eloadas.builder()
                .date(new Date())
                .name("Sürgősségi gyermekgyógyászat")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(surgossegiGyermekgyogyaszat);

        Eloadas gyermekpulmonologia = Eloadas.builder()
                .date(new Date())
                .name("Gyermekpulmonológia")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(gyermekpulmonologia);

        Eloadas  neonatalogia= Eloadas.builder()
                .date(new Date())
                .name("Neonatalógia,Kardiológia")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(neonatalogia);

        Eloadas  infektologia= Eloadas.builder()
                .date(new Date())
                .name("Infektológia")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(infektologia);

        Eloadas  gasztroenterológia= Eloadas.builder()
                .date(new Date())
                .name("Gasztroenterológia,hepatológia")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(gasztroenterológia);

        Eloadas  nefrologia= Eloadas.builder()
                .date(new Date())
                .name("Nefrológia")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(nefrologia);

        Eloadas  neurologia= Eloadas.builder()
                .date(new Date())
                .name("Neurológia")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(neurologia);

        Eloadas  endokrinologia= Eloadas.builder()
                .date(new Date())
                .name("Endokrinológia")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(endokrinologia);

        Eloadas  sebeszet= Eloadas.builder()
                .date(new Date())
                .name("Sebészet,Traumatológia")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(sebeszet);

        Eloadas  borgyogyaszat= Eloadas.builder()
                .date(new Date())
                .name("Bőrgyógyászat,Immunológia")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(borgyogyaszat);

        Eloadas  pszichiátria= Eloadas.builder()
                .date(new Date())
                .name("Pszichiátria")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(pszichiátria);

        Eloadas  genetika= Eloadas.builder()
                .date(new Date())
                .name("Genetika,Anyagcserebetegségek")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .active(true)
                .build();
        eloadasRepository.save(genetika);

        Eloadas  introduction= Eloadas.builder()
                .date(new Date())
                .name("Introduction")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(introduction);

        Eloadas  development= Eloadas.builder()
                .date(new Date())
                .name("Development,screening,immunisation,Radiology")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(development);

        Eloadas  pediatric= Eloadas.builder()
                .date(new Date())
                .name("Pediatric emergencies")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(pediatric);

        Eloadas  pulmonology= Eloadas.builder()
                .date(new Date())
                .name("Pulmonology")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(pulmonology);

        Eloadas  neonatalogy= Eloadas.builder()
                .date(new Date())
                .name("Neonatalogy,Cardiology")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(neonatalogy);

        Eloadas  infectiousDiseases= Eloadas.builder()
                .date(new Date())
                .name("Infectious diseases")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(infectiousDiseases);

        Eloadas  gastroenterology= Eloadas.builder()
                .date(new Date())
                .name("Gastroenterology,hepatology")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(gastroenterology);

        Eloadas  nephrology= Eloadas.builder()
                .date(new Date())
                .name("Nephrology")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(nephrology);

        Eloadas  neurology= Eloadas.builder()
                .date(new Date())
                .name("Neurology")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(neurology);

        Eloadas  endocrinology= Eloadas.builder()
                .date(new Date())
                .name("Endocrinology")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(endocrinology);

        Eloadas  surgery= Eloadas.builder()
                .date(new Date())
                .name("Surgery,Traumatology")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(surgery);

        Eloadas  dermatology= Eloadas.builder()
                .date(new Date())
                .name("Dermatology,Immunonogy")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(dermatology);

        Eloadas  psychiatry= Eloadas.builder()
                .date(new Date())
                .name("Psychiatry")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(psychiatry);

        Eloadas  genetics= Eloadas.builder()
                .date(new Date())
                .name("Genetics,Inborn errors of metabolism")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .active(true)
                .build();
        eloadasRepository.save(genetics);


        //Konzultáció generálás, és hozzáadás
        Konzultacio konzultacio1 = Konzultacio.builder()
                .name("Konzultáció")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        konzultacioRepository.save(konzultacio1);

        Konzultacio konzultacio2 = Konzultacio.builder()
                .name("Konzultáció")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        konzultacioRepository.save(konzultacio2);

        Konzultacio konzultacio3 = Konzultacio.builder()
                .name("Konzultáció")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        konzultacioRepository.save(konzultacio3);

        Konzultacio konzultacio4 = Konzultacio.builder()
                .name("Konzultáció")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        konzultacioRepository.save(konzultacio4);

        Konzultacio konzultacio5 = Konzultacio.builder()
                .name("Konzultáció")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        konzultacioRepository.save(konzultacio5);

        Konzultacio konzultacio6 = Konzultacio.builder()
                .name("Konzultáció")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        konzultacioRepository.save(konzultacio6);

        Konzultacio konzultacio7 = Konzultacio.builder()
                .name("Konzultáció")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        konzultacioRepository.save(konzultacio7);

        Konzultacio konzultacio8 = Konzultacio.builder()
                .name("Konzultáció")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        konzultacioRepository.save(konzultacio8);

        Konzultacio konzultacio9 = Konzultacio.builder()
                .name("Konzultáció")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        konzultacioRepository.save(konzultacio9);

        Konzultacio konzultacio10 = Konzultacio.builder()
                .name("Konzultáció")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        konzultacioRepository.save(konzultacio10);

        Konzultacio konzultacio11 = Konzultacio.builder()
                .name("Konzultáció")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        konzultacioRepository.save(konzultacio11);

        Konzultacio konzultacio12 = Konzultacio.builder()
                .name("Konzultáció")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        konzultacioRepository.save(konzultacio12);

        Konzultacio consultation1 = Konzultacio.builder()
                .name("Consultation")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        konzultacioRepository.save(consultation1);

        Konzultacio consultation2 = Konzultacio.builder()
                .name("Consultation")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        konzultacioRepository.save(consultation2);

        Konzultacio consultation3 = Konzultacio.builder()
                .name("Consultation")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        konzultacioRepository.save(consultation3);

        Konzultacio consultation4 = Konzultacio.builder()
                .name("Consultation")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        konzultacioRepository.save(consultation4);

        Konzultacio consultation5 = Konzultacio.builder()
                .name("Consultation")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        konzultacioRepository.save(consultation5);

        Konzultacio consultation6 = Konzultacio.builder()
                .name("Consultation")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        konzultacioRepository.save(consultation6);

        Konzultacio consultation7 = Konzultacio.builder()
                .name("Consultation")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        konzultacioRepository.save(consultation7);

        Konzultacio consultation8 = Konzultacio.builder()
                .name("Consultation")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        konzultacioRepository.save(consultation8);

        Konzultacio consultation9 = Konzultacio.builder()
                .name("Consultation")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        konzultacioRepository.save(consultation9);

        Konzultacio consultation10 = Konzultacio.builder()
                .name("Consultation")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        konzultacioRepository.save(consultation10);

        Konzultacio consultation11 = Konzultacio.builder()
                .name("Consultation")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        konzultacioRepository.save(consultation11);

        //Gyakorlat generálás, és hozzáadás
        Gyakorlat gyakorlat1 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat1);

        Gyakorlat specgyakorlatReumatologia = Gyakorlat.builder()
                .name("Spec gyakorlat-Reumatológiai betegvizsgálat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specgyakorlatReumatologia);

        Gyakorlat gyakorlat2 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat2);

        Gyakorlat specgyakorlatNeurologia = Gyakorlat.builder()
                .name("Spec gyakorlat-Neurológiai betegvizsgálat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specgyakorlatNeurologia);

        Gyakorlat gyakorlat3 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat3);

        Gyakorlat specgyakorlatHematologia = Gyakorlat.builder()
                .name("Spec gyakorlat-Hematológia")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specgyakorlatHematologia);

        Gyakorlat gyakorlat4 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat4);

        Gyakorlat specgyakorlatEndokrinologia = Gyakorlat.builder()
                .name("Spec gyakorlat-Endokrinológia")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specgyakorlatEndokrinologia);

        Gyakorlat gyakorlat5 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat5);

        Gyakorlat gyakorlat6 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat6);

        Gyakorlat specgyakorlatGasztroenterologia = Gyakorlat.builder()
                .name("Spec gyakorlat-Gasztroenterológia")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specgyakorlatGasztroenterologia);

        Gyakorlat gyakorlat7 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat7);

        Gyakorlat gyakorlat8 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat8);

        Gyakorlat gyakorlat9 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat9);

        Gyakorlat specgyakorlatOnkologia = Gyakorlat.builder()
                .name("Spec gyakorlat-Onkológia")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specgyakorlatOnkologia);

        Gyakorlat gyakorlat10 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat10);

        Gyakorlat gyakorlat11 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat11);

        Gyakorlat specgyakorlatSebeszet = Gyakorlat.builder()
                .name("Spec gyakorlat-Sebészet")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specgyakorlatSebeszet);

        Gyakorlat gyakorlat12 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat12);

        Gyakorlat specgyakorlatKardiologia = Gyakorlat.builder()
                .name("Spec gyakorlat-Kardiológia")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specgyakorlatKardiologia);

        Gyakorlat gyakorlat13 = Gyakorlat.builder()
                .name("Osztályos gyakorlat")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(gyakorlat13);

        Gyakorlat practice1 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice1);

        Gyakorlat specPracticeRheumatology = Gyakorlat.builder()
                .name("Spec practice-Rheumatology")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specPracticeRheumatology);

        Gyakorlat practice2 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice2);

        Gyakorlat specPracticeNeurology = Gyakorlat.builder()
                .name("Spec practice-Neurology")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specPracticeNeurology);

        Gyakorlat practice3 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice3);

        Gyakorlat specPracticeHematology = Gyakorlat.builder()
                .name("Spec practice-Hematology")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specPracticeHematology);

        Gyakorlat practice4 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice4);

        Gyakorlat specPracticeCardiology = Gyakorlat.builder()
                .name("Spec practice-Cardiology")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specPracticeCardiology);

        Gyakorlat practice5 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice5);

        Gyakorlat practice6 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice6);

        Gyakorlat specPracticeEndocrinology = Gyakorlat.builder()
                .name("Spec practice-Endocrinology")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specPracticeEndocrinology);

        Gyakorlat practice7 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice7);

        Gyakorlat specPracticeCommunication = Gyakorlat.builder()
                .name("Spec practice-Communication skills")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specPracticeCommunication);

        Gyakorlat practice8 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice8);

        Gyakorlat practice9 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice9);

        Gyakorlat specPracticeInfectology = Gyakorlat.builder()
                .name("Spec practice-Infectology")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specPracticeInfectology);

        Gyakorlat practice10 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice10);

        Gyakorlat specPracticeOncology = Gyakorlat.builder()
                .name("Spec practice-Oncology")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specPracticeOncology);

        Gyakorlat practice11 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice11);

        Gyakorlat specPracticeSurgery = Gyakorlat.builder()
                .name("Spec practice-Surgery")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specPracticeSurgery);

        Gyakorlat practice12 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice12);

        Gyakorlat  specPracticeGastroenterology= Gyakorlat.builder()
                .name("Spec practice-Gastroenterology")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 2)
                .build();
        gyakorlatRepository.save(specPracticeGastroenterology);

        Gyakorlat practice13 = Gyakorlat.builder()
                .name("Practice")
                .active(true)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("QWERTZ1")
                .build();
        gyakorlatRepository.save(practice13);



        List<Gyakorlat> gyakorlatListt1 = gyakorlatRepository.findAllByTurnusId((long) 1);
        List<Konzultacio> konzultacioListt1 = konzultacioRepository.findAllByTurnusId((long) 1);
        List<Eloadas> eloadasListt1 = eloadasRepository.findAllByTurnusId((long) 1);

        Student student1t1 = Student.builder()
                .email("kovacspeter@student.com")
                .name("Kovács Péter")
                .neptunCode("WQ45DS")
                .password(passwordEncoder.encode("password"))
                .turnusId((long) 1)
                .gyakorlatList(gyakorlatListt1.subList(0, 2))
                .eloadasList(eloadasListt1.subList(0, 1))
                .konzultacioList(konzultacioListt1.subList(0, 0))
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();
        Student student2t1 = Student.builder()
                .email("szaboa92@student.com")
                .name("Szabó Andrea")
                .neptunCode("LK34RP")
                .password(passwordEncoder.encode("password"))
                .turnusId((long) 1)
                .gyakorlatList(gyakorlatListt1.subList(0, 12))
                .eloadasList(eloadasListt1.subList(0, 13))
                .konzultacioList(konzultacioListt1.subList(0, 9))
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();
        Student student3t1 = Student.builder()
                .email("takácsp@student.com")
                .name("Takács Péter")
                .neptunCode("DIRA45")
                .password(passwordEncoder.encode("password"))
                .turnusId((long) 1)
                .gyakorlatList(gyakorlatListt1.subList(0, 11))
                .eloadasList(eloadasListt1.subList(0, 12))
                .konzultacioList(konzultacioListt1.subList(0, 10))
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();
        Student student4t1 = Student.builder()
                .email("kandras92@student.com")
                .name("Kovács András")
                .neptunCode("A45RTQ3")
                .password(passwordEncoder.encode("password"))
                .turnusId((long) 1)
                .gyakorlatList(gyakorlatListt1)
                .eloadasList(eloadasListt1)
                .konzultacioList(konzultacioListt1)
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();
        Student student5t1 = Student.builder()
            .email("bence.hausknecht@gmail.com")
            .name("Bence Hausknecht")
            .neptunCode("DASD32")
            .password(passwordEncoder.encode("password"))
            .turnusId((long) 1)
            .gyakorlatList(gyakorlatListt1)
            .eloadasList(eloadasListt1)
            .konzultacioList(konzultacioListt1)
            .roles(List.of(ApplicationUserRole.STUDENT))
            .build();

        studentRepository.save(student1t1);
        studentRepository.save(student2t1);
        studentRepository.save(student3t1);
        studentRepository.save(student4t1);
        studentRepository.save(student5t1);
    }
}
