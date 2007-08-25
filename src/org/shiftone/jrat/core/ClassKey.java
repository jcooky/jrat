// Copyright 2007 Google Inc. All Rights Reserved.

package org.shiftone.jrat.core;

import java.util.*;
import java.io.Serializable;

/**
 * @author jeff@shiftone.org (Jeff Drost)
 */
public class ClassKey implements Serializable, Comparable {
    private static final long serialVersionUID = 1;
    private String packageName;
    private String className;
    private int hashCode;

    private static final Map CACHE = new HashMap();  //<String, ClassKey>

    public static ClassKey getInstance(String fullyQualifiedClassName) {
       ClassKey classKey = (ClassKey) CACHE.get(fullyQualifiedClassName);
        if (classKey == null) {
            classKey = new ClassKey(fullyQualifiedClassName);
        }
        return classKey;
    }

    private ClassKey(String fullyQualifiedClassName) {

        int dot = fullyQualifiedClassName.lastIndexOf('.');
        this.packageName = (dot == -1)
                ? ""
                : fullyQualifiedClassName.substring(0, dot).intern();

        this.className = (dot == -1)
                ? fullyQualifiedClassName
                : fullyQualifiedClassName.substring(dot + 1);

        hashCode = packageName.hashCode();
        hashCode = (29 * hashCode) + className.hashCode();
    }



     /**
     * Gets the package's name in pieces.
     */
    public String[] getPackageNameParts() {
        String[] result = new String[0];
        if (packageName.length() != 0) {
            StringTokenizer st = new StringTokenizer(packageName, ".");
            List parts = new ArrayList();
            while (st.hasMoreTokens()) {
                parts.add(st.nextToken());
            }
            result = (String[]) parts.toArray(new String[parts.size()]);
        }
        return result;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public final boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof ClassKey)) {
            return false;
        }

        final ClassKey classKey = (ClassKey) o;

        if (!classKey.equals(classKey)) {
            return false;
        }

        if (!className.equals(classKey.className)) {
            return false;
        }

        if (!packageName.equals(classKey.packageName)) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        return hashCode;
    }

    public int compareTo(Object o) {
        ClassKey other = (ClassKey) o;
        int c = packageName.compareTo(other.packageName);
        if (c == 0) {
            c = className.compareTo(other.className);
        }
        return c;
    }

}