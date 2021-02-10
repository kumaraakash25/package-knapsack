package com.mobiquity.packer;

import com.mobiquity.exception.APIException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PackerTest {

    private List<String> inputLines;
    private List<String> outputLines;

    @Before
    public void setup() {
        inputLines = readLinesFromResource("input-sample.txt");
        outputLines = readLinesFromResource("output-sample.txt");
    }

    @After
    public void destroy() {
        inputLines = null;
        outputLines = null;
    }

    @Test
    public void testResources() {
        assertNotNull(inputLines);
        assertNotNull(outputLines);
        assertEquals(inputLines.size(), outputLines.size());
    }

    @Test
    public void testPacker_happyCase() {
        for ( int i = 0; i < inputLines.size(); i++ ) {
            try {
                assertEquals( outputLines.get(i), Packer.pack(inputLines.get(i)) );
            } catch (APIException e) {
                fail(e.getMessage());
            }
        }
    }

    private List<String> readLinesFromResource(String filename) {
        ClassLoader  loader = PackerTest.class.getClassLoader();
        List<String> lines;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    loader.getResourceAsStream(filename), "UTF-8"
            ));
            lines = br.lines().parallel().map(Object::toString).collect(Collectors.toList());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            lines = null;
        }
        return lines;
    }
}