package com.assig_junit;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)  
class InterfaceTest {

    @Mock
    I mockObj;

    @Test
    void testInterfaceMethodCall() {

        mockObj.abc();

        verify(mockObj).abc();
    }

    @Test
    void testVoidMethod() {

        mockObj.abc();

        verify(mockObj, times(1)).abc();
    }

    @Test
    void testMethodCallCount() {

        mockObj.abc();
        mockObj.abc();
        mockObj.abc();

        verify(mockObj, times(3)).abc();
    }
}