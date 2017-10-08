package com.example.securitydemo;

import com.example.securitydemo.entities.Role;
import com.example.securitydemo.repositories.RoleRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static junit.framework.TestCase.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleRepositoryTest extends SecurityDemoApplicationTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void test1ListRoles(){
        assertNotNull(this.roleRepository.listRoles());
    }

    @Test
    public void test10AddRole_null_role(){
        Role dummyRole = null;
        this.roleRepository.saveOrUpdateRole(dummyRole);
    }

    @Test
    public void test11AddRole_blank_role(){
        Role dummyRole = new Role();
        this.roleRepository.saveOrUpdateRole(dummyRole);
    }

    @Test
    public void test12AddRole_only_code(){
        Role dummyRole = new Role();
        dummyRole.setCode("DUMMY1");
        dummyRole.setLastModifiedDate(new Date());
        this.roleRepository.saveOrUpdateRole(dummyRole);
    }

    @Test
    public void test13AddRole_code_name(){
        Role dummyRole = new Role();
        dummyRole.setCode("DUMMY2");
        dummyRole.setName("Dummy Role 2");
        dummyRole.setLastModifiedDate(new Date());
        this.roleRepository.saveOrUpdateRole(dummyRole);
    }

    @Test
    public void test14AddRole_code_name_description(){
        Role dummyRole = new Role();
        dummyRole.setCode("DUMMY3");
        dummyRole.setName("Dummy Role 3");
        dummyRole.setDescription("This is just a dummy role for testing.");
        dummyRole.setLastModifiedDate(new Date());
        this.roleRepository.saveOrUpdateRole(dummyRole);
    }

    @Test
    public void test15AddRole_code_name_description_date(){
        Role dummyRole = new Role();
        dummyRole.setCode("DUMMY4");
        dummyRole.setName("Dummy Role 4");
        dummyRole.setDescription("This is just a dummy role for testing.");
        dummyRole.setCreateDate(new Date());
        dummyRole.setLastModifiedDate(new Date());
        this.roleRepository.saveOrUpdateRole(dummyRole);
    }

    @Test
    public void test16GetRole_null_code(){
        assertNull(this.roleRepository.getRole(null));
    }

    @Test
    public void test17GetRole_blank_code(){
        assertNull(this.roleRepository.getRole(""));
    }

    @Test
    public void test18GetRole_not_exist_code(){
        assertNull(this.roleRepository.getRole("burikat"));
    }

    @Test
    public void test19GetRole_exist_code(){
        assertNotNull(this.roleRepository.getRole("DUMMY1"));
    }

    @Test
    public void test20UpdateRole_null(){
        Role dummyUpdateRole = null;
        assertFalse(this.roleRepository.updateRole(dummyUpdateRole));
    }

    @Test
    public void test21UpdateRole_blank(){
        Role dummyUpdateRole = new Role();
        assertFalse(this.roleRepository.updateRole(dummyUpdateRole));
    }

    @Test
    public void test22UpdateRole_code(){
        Role dummyUpdateRole = new Role();
        dummyUpdateRole.setCode("DUMMY1");
        assertTrue(this.roleRepository.updateRole(dummyUpdateRole));
    }

    @Test
    public void test23UpdateRole_code_description(){
        Role dummyUpdateRole = new Role();
        dummyUpdateRole.setCode("DUMMY2");
        dummyUpdateRole.setDescription("Orayt! This is the update role description.");
        assertTrue(this.roleRepository.updateRole(dummyUpdateRole));
    }

    @Test
    public void test24UpdateRole_code_description_lastmodifieddate(){
        Role dummyUpdateRole = new Role();
        dummyUpdateRole.setCode("DUMMY3");
        dummyUpdateRole.setDescription("Orayt! This is the update role description.");
        dummyUpdateRole.setLastModifiedDate(new Date());
        assertTrue(this.roleRepository.updateRole(dummyUpdateRole));
    }

    @Test
    public void test25DeleteRole_null(){
        Role dummyDeleteRole = null;
        assertFalse(this.roleRepository.deleteRole(dummyDeleteRole));
    }

    @Test
    public void test26DeleteRole_blank(){
        Role dummyDeleteRole = new Role();
        assertFalse(this.roleRepository.deleteRole(dummyDeleteRole));
    }

    @Test
    public void test27DeleteRole_code(){
        Role dummyDeleteRole = new Role();
        dummyDeleteRole.setCode("DUMMY3");
        assertTrue(this.roleRepository.deleteRole(dummyDeleteRole));
    }

    @Test
    public void test28RemoveOtherDummyData(){
        assertTrue(this.roleRepository.deleteRole(new Role("DUMMY1")));
        assertTrue(this.roleRepository.deleteRole(new Role("DUMMY2")));
        assertTrue(this.roleRepository.deleteRole(new Role("DUMMY4")));
    }

/*
    @Test
    public void testCreateRoleAdminUserActuator(){
        Role adminRole = new Role();
        adminRole.setCode("ADMIN");
        adminRole.setName("Administrator Role");
        adminRole.setDescription("Administrator Role");
        adminRole.setCreateDate(new Date());
        adminRole.setLastModifiedDate(new Date());
        this.roleRepository.saveOrUpdateRole(adminRole);

        Role actuatorRole = new Role();
        actuatorRole.setCode("ACTUATOR");
        actuatorRole.setName("Actuator Role");
        actuatorRole.setDescription("Actuator Role");
        actuatorRole.setCreateDate(new Date());
        actuatorRole.setLastModifiedDate(new Date());
        this.roleRepository.saveOrUpdateRole(actuatorRole);

        Role userRole = new Role();
        userRole.setCode("USER");
        userRole.setName("User Role");
        userRole.setDescription("User Role");
        userRole.setCreateDate(new Date());
        userRole.setLastModifiedDate(new Date());
        this.roleRepository.saveOrUpdateRole(userRole);
    }
*/

}