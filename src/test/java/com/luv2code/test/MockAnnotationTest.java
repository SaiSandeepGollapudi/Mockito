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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)//Loads Spring Application Context
//if test class is in a different package like this, Explicitly reference main SpringBoot class
// If you give same package names as your main package.No need to explicitly reference the main Spring Boot application class
public class MockAnnotationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

  //  @Mock
  @MockBean
    private ApplicationDao applicationDao;

   // @InjectMocks this will only inject the dependencies that are annotated with mock or spy. It won't handle other classes that are auto wired without those dependencies,
 @Autowired//Spring Boot @MockBean .Instead of using Mockito: @Mock and @InjectMocks Use Spring Boot support: @MockBean and @Autowired
//    @MockBean includes Mockito @Mock functionality also adds mock bean to Spring ApplicationContext
//if existing bean is there, the mock bean will replace it thus making the mock bean available for injection with @Autowired
 //Use Spring Boot @MockBean when you need to inject mocks AND inject regular beans from app context
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
        when(applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults())).thenReturn(100.00);//This line is setting up a mock behavior using Mockito's
        // when(...).thenReturn(...) syntax. It's essentially configuring a mock of your applicationDao object to return a specific value (100.00)
//      when method addGradeResultsForSingleClass(...) is called then return 100.0,  we're basically setting up some mock code here,the response can be a string,it can be a Boolean,
//        it could be an ArrayList,it could be an Object.
//the first line is setting up a mock behavior for a method call on the DAO layer, while the second line is invoking the corresponding method on the service layer and testing its behavior
// based on the mock setup. This isolation allows you to focus specifically on the behavior of the service layer method without being affected by the actual behavior of the DAO layer method.
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
@DisplayName("Find Gpa")
    @Test
public void assertEqualsTestFindGpa(){
        when(applicationDao.findGradePointAverage(studentGrades.getMathGradeResults())).thenReturn(88.31);
        assertEquals(88.31, applicationService.findGradePointAverage(studentOne.getStudentGrades().getMathGradeResults()));
}

@DisplayName("Not Null")
    @Test
    public void testAssertNotNull (){
when(applicationDao.checkNull(studentGrades.getMathGradeResults())).thenReturn(true);
assertNotNull(applicationService.checkNull(studentOne.getStudentGrades().getMathGradeResults()),"object should not null");
}
    }


