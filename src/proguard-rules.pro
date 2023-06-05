# Add any ProGuard configurations specific to this
# extension here.

-keep public class com.yash.advancescroll.AdvanceScroll {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'com/yash/advancescroll/repack'
-flattenpackagehierarchy
-dontpreverify
