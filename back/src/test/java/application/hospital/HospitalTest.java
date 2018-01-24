package application.hospital;

import application.hospital.domain.HospitalImpl;
import org.codehaus.jackson.map.JsonMappingException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class HospitalTest{
/*
    @Test(expected = NullPointerException.class)
    public void testInitHierarcgyWithPathNull(){
        Hospital hospital = new HospitalImpl("","","" );
        hospital.initHierarchy(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitHierarchyWithDirectoryPathInsteadOfFilePath(){
        Path path = Paths.get("../java/");
        Hospital hospital = new HospitalImpl("","","" );
        hospital.initHierarchy(path);
    }

    @Test(expected = IOException.class)
    public void testInitHierarchyErrorOpeningFile(){
        Path path = Paths.get("../ressources/fileCantBeOpen.json");
        Hospital hospital = new HospitalImpl("","","" );
        hospital.initHierarchy(path);
    }

    @Test(expected = IOException.class)
    public void testInitHierarchyErrorReadingFile(){
        Path path = Paths.get("../ressources/fileCantBeRead.json");
        Hospital hospital = new HospitalImpl("","","" );
        hospital.initHierarchy(path);
    }

    @Test(expected = IOException.class)
    public void testInitHierarchyWithWrongJsonFormatFilePath(){
        Path path = Paths.get("../ressources/fileWithWrongJsonFormat.json");
        Hospital hospital = new HospitalImpl("","","" );
        hospital.initHierarchy(path);
    }

    @Test(expected = JsonMappingException.class)
    public void testInitWithWrongJsonFormatFilePath(){
        Path path = Paths.get("../ressources/recettar.json");
        Hospital hospital = new HospitalImpl("","","" );
        hospital.initHierarchy(path);
    }

    @Test
    public void testInitHierarchy(){
        Path path = Paths.get("../ressources/hospitalHenryMondor.json");
        Hospital hospital = new HospitalImpl("","","" );
        hospital.initHierarchy(path);
        //todo mock and assert
    }
*/
}
