package edu.sctu.graduation.utils;

        public class DesUtils {
    /**
     * 使用默认密钥进行DES加密
     */
    public static String encrypt(String plainText) {
        try {
            return new DES().encrypt(plainText);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 使用指定密钥进行DES解密
     */
    public static String encrypt(String plainText, String key) {
        try {
            return new DES(key).encrypt(plainText);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 使用默认密钥进行DES解密
     */
    public static String decrypt(String plainText) {
        try {
            return new DES().decrypt(plainText);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 使用指定密钥进行DES解密
     */
    public static String decrypt(String plainText, String key) {
        try {
            return new DES(key).decrypt(plainText);
        } catch (Exception e) {
            return null;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(DesUtils.encrypt("18328023100"));//dc3eadd7a79bc62f7e21fa9e67ea1cdf
//        System.out.println(DesUtils.encrypt("18328023101"));//dc3eadd7a79bc62f4dca06be047d43b4
//        System.out.println(DesUtils.encrypt("18328023102"));//dc3eadd7a79bc62f99bc9bbd4883c1e5
//        System.out.println(DesUtils.encrypt("18328023103"));//dc3eadd7a79bc62f15f50f5b19b6dea4
//        System.out.println(DesUtils.encrypt("18328023199"));//dc3eadd7a79bc62fd30498b3ba6b9e9f
//        System.out.println(DesUtils.encrypt("qaz147"));//70e55b00ede33bb4
//    }

//    public static void main(String[] args) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        String date = dateFormat.format(new Date());
//
//        System.out.println("ssssss" +date);
//    }

}
