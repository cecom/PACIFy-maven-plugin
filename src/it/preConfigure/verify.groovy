File expectedResult= new File (basedir, "expectedResult");
File actual= new File (basedir, "target/package");

com.geewhiz.pacify.test.TestUtil.checkIfResultIsAsExpected(actual,expectedResult);