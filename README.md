# encryption-algorithms
AES, DES, RSA

* test as follow
--- java 
    public static void main(String[] args) throws Exception {
        //------ AES
        String input = "AES is strongest sync encryption algo";
        //initialVector.length = key.length
        String key = "1231231231231231";
        String initialVector = "dect7f6e-3b47ge2";

        byte[] encrypted = AES.encrypt(key, initialVector, input.getBytes());
        byte[] decrypted = AES.decrypt(key, initialVector, encrypted);
        System.out.println("AES test result: " + new String(decrypted));

        //------------- 3DES
        input = "3DES is another strong sync encryption algo";
        String DESbase64Key = "6lRqh2yaYyc1NV2oCcqltC0hYp/9Lnn5";
        String encryptedBase63_3DES = ThripleDESBase64.encrypt(DESbase64Key, input);
        String decrypted3DES = ThripleDESBase64.decrypt(DESbase64Key, encryptedBase63_3DES);
        System.out.println("3DES test result:" + decrypted3DES);


        //--------- RSA
        RSA.init("/home/vahid/.ssh/id_rsa.pub", "/home/vahid/.ssh/id_rsa");

    }

---
