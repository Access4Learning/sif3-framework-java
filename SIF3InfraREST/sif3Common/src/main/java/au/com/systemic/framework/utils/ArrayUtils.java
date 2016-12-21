/*
 * ArrayUtils.java
 *
 * Copyright 2003-2014 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package au.com.systemic.framework.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Joerg Huber
 */
public class ArrayUtils
{
  /**
   * This method adds the right collecteion at the end of the left collection. If one of the collections
   * is null or empty then the other one is returned. If both are null or empty then a empty  collection
   * is returned. Basically 'null' will never be returned.
   * 
   * @param left Collection<T>
   * @param right Collection<T>
   * 
   * @return See Description
   */
  public static <T> Collection<T> add(Collection<T> left, Collection<T> right)
  {
    if (left == null)
    {
       if (right == null)
       {
         return new ArrayList<T>();
       }
      
       return right;
    }
    
    if (left.size() == 0)
    {
      return ((right == null) ? left : right);
    }
    
    if ((right == null) || (right.size() == 0))
    {
      return left;
    }
    
    // If we get here both collections hold some values.
    left.addAll(right);

    return left;
  }
  
  /**
   * This method adds the right List at the end of the left List. If one of the Lists
   * is null or empty then the other one is returned. If both are null or empty then a empty List
   * is returned. Basically 'null' will never be returned.
   * 
   * @param left List<T>
   * @param right List<T>
   * 
   * @return See Description
   */
  public static <T> List<T> add(List<T> left, List<T> right)
  {
    if (left == null)
    {
       if (right == null)
       {
         return new ArrayList<T>();
       }
      
       return right;
    }
    
    if (left.size() == 0)
    {
      return ((right == null) ? left : right);
    }
    
    if ((right == null) || (right.size() == 0))
    {
      return left;
    }
    
    // If we get here both collections hold some values.
    left.addAll(right);

    return left;
  }
  
}
