package com.jacky.algorithm.基础班.有序表;


/**
 * <p>
 * 1)有序表在使用层面上可以理解为一种集合结构
 * 2)如果只有key，没有伴随数据value，可以使用TreeSet结构
 * 3)如果既有key，又有伴随数据value，可以使用TreeMap结构
 * 4)有无伴随数据，是TreeSet和TreeMap唯一的区别，底层的实际结构是一回事
 * 5)有序表把key按照顺序组织起来，而哈希表完全不组织
 *
 *
 * 6)红黑树、AVL树、size-balance-tree和跳表等都属于有序表结构，只是底层具体实现不同
 * 7)放入如果是基础类型，内部按值传递，内存占用就是这个东西的大小
 * 8)放入如果不是基础类型，内部按引用传递，内存占用是8字节
 * 9)不管是什么底层具体实现，只要是有序表，都有以下固定的基本功能和固定的时间复杂度
 *
 *
 * 1)void put(K key, V value)
 * 将一个(key，value)记录加入到表中，或者将key的记录 更新成value。
 * 2)V get(K key)
 * 根据给定的key，查询value并返回。
 * 3)void remove(K key)
 * 移除key的记录。
 * 4)boolean containsKey(K key)
 * 询问是否有关于key的记录。
 *
 *
 * 5)K firstKey()
 * 返回所有键值的排序结果中，最小的那个。  6)K lastKey()
 * 返回所有键值的排序结果中，最大的那个。 7)K floorKey(K key)
 * 返回<= key 离key最近的那个
 * 8)K ceilingKey(K key）
 * 返回>= key 离key最近的那个
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/24
 **/