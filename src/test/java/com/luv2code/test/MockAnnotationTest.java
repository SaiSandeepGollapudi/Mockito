package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class MockAnnotationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

    @Mock
    private ApplicationDao applicationDao;

    @InjectMocks//this will only inject the dependencies that are annotated with mock or spy. It won't handle other classes that are auto wired without those dependencies,
  private ApplicationService applicationService;

    @BeforeEach
    public void beforeEach(){
        studentOne.setFirstname("Eric");
        studentOne.setLastname("Roby");
        studentOne.setEmailAddress("eric@gmail.com");
        studentOne.setStudentGrades(studentGrades);
    }

    @DisplayName("When & Verify")
        @Test
                public void assertEqualsTestAddGrades(){
        ApplicationDao appicationDao;
        when(applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults())).thenReturn(100.00);//
//      when method addGradeResultsForSingleClass(...) is called then return 100.0,  we're basically setting up some mock code here,the response can be a string,it can be a Boolean,
//        it could be an ArrayList,it could be an Object.

assertEquals(100, applicationService.addGradeResultsForSingleClass(studentOne.getStudentGrades().getMathGradeResults()));
//        we're calling this service.
//        The service makes use of the DAO.
//                We've already mocked the DAO
//        for that given method to return 100.
//        And so this given test will pass accordingly.
verify(applicationDao, times(1)).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
//I'll say verify ApplicationDao times one.
//You can use this in your application,
//you could say for this given logic,
//we should have called a given method X number of times
//and you can kind of use that to verify that information.


    }
    }


