@file:Suppress("UNUSED_PARAMETER")

package lesson7

import java.io.BufferedReader
import java.io.FileReader

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
fun longestCommonSubSequence(first: String, second: String): String {
    TODO()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 *
 * Трудоемкость - O(n^2)
 * Ресурсоемкость - О(n)
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    val n = list.size
    val prev = MutableList(n) { it }
    val d = MutableList(n) { it }
    if (list.isEmpty()) return list

    for (i in 0 until n) {
        d[i] = 1
        prev[i] = -1
        for (j in 0 until i) {
            if ((list[j] < list[i]) and (d[j] + 1 > d[i])) {
                d[i] = d[j] + 1
                prev[i] = j
            }
        }
    }
    var pos = 0

    var leng = d[0]

    for (i in 0 until n) {
        if (d[i] > leng) {
            pos = i
            leng = d[i]
        }
    }
    val ans = mutableListOf<Int>()
    while (pos != -1) {
        ans.add(list[pos])
        pos = prev[pos]
    }
    return ans.reversed()
}


/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 * Трудоемкость - O(m*n)
 * Ресурсоемкость - О(n)
 * n - кол-во столбцов, m - рядов
 */
fun shortestPathOnField(inputName: String): Int {
    val reader = BufferedReader(FileReader(inputName))
    var fieldRow = mutableListOf<Int>()
    for (num in reader.readLine().split(" ")) {
        fieldRow.add(num.toInt())
    }

    val columns = fieldRow.size - 1
    for (i in 2..columns) fieldRow[i] += fieldRow[i - 1]

    for (line in reader.readLines()) {

        val newLine = mutableListOf<Int>()
        for (num in line.split(" ")) {
            newLine.add(num.toInt())
        }

        for (i in 0..columns) {
            if (i == 0)
                newLine[i] += fieldRow[i]
            else
                newLine[i] += minOf(newLine[i - 1], fieldRow[i], fieldRow[i - 1])
        }

        fieldRow = newLine
    }
    return fieldRow[columns]
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5