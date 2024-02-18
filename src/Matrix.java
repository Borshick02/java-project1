package org.example;
import java.util.ArrayList;

/** класс работает как с комплексными, как и с действительными числами */

public class Matrix{
    private final int column;

    private final int row;

    ArrayList<ArrayList<ComplexNumber>> matrix;


    Matrix(int row, int column, ArrayList<ArrayList<ComplexNumber>> matrix) {
        this.column = column;
        this.row = row;
        this.matrix = matrix;
    }


    StringBuilder MatrixAsStr() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < row; i++){
            for (ComplexNumber n : matrix.get(i)) {
                str.append(n.algebraicForm()).append(" ");
            }
            str.append('\n');
        }
        return str;
    }


    Matrix mulNumber(ComplexNumber number) {
        var mtrx = new ArrayList<ArrayList<ComplexNumber>> ();
        for (int i = 0; i < row; i++) {
            var mas = new ArrayList<ComplexNumber>();
            for (int j = 0; j < column; j ++){
                mas.add(matrix.get(i).get(j).mul(number));
            }
            mtrx.add(mas);
        }
        return new Matrix(row, column, mtrx);
    }


    Matrix addMatrix(Matrix another) {
        if (row != another.row || column != another.column)
            throw new RuntimeException("Размеры матрицы не совпадают");

        var mtrx = new ArrayList<ArrayList<ComplexNumber>> ();
        for (int i = 0; i < row; i++) {
            var mas = new ArrayList<ComplexNumber>();
            for (int j = 0; j < column; j ++){
                mas.add(matrix.get(i).get(j).add(another.matrix.get(i).get(j)));
            }
            mtrx.add(mas);
        }
        return new Matrix(row, column, mtrx);
    }


    Matrix decrMatrix(Matrix another) {
        if (row != another.row || column != another.column)
            throw new RuntimeException("Размеры матрицы не совпадают");

        var mtrx = new ArrayList<ArrayList<ComplexNumber>> ();
        for (int i = 0; i < row; i++) {
            var mas = new ArrayList<ComplexNumber>();
            for (int j = 0; j < column; j ++){
                mas.add(matrix.get(i).get(j).decr(another.matrix.get(i).get(j)));
            }
            mtrx.add(mas);
        }
        return new Matrix(row, column, mtrx);
    }


    Matrix mulMatrix(Matrix another) {
        if (column != another.row)
            throw new RuntimeException("Размеры матрицы не совпадают");

        var mtrx = new ArrayList<ArrayList<ComplexNumber>> ();
        for (int i = 0; i < row; i++) {
            var mas = new ArrayList<ComplexNumber>();
            for (int j = 0; j < another.column; j ++){
                var n = new ComplexNumber();
                for (int s = 0; s < row; s++) {
                    n = n.add(matrix.get(i).get(s).mul(another.matrix.get(s).get(j)));
                }
                mas.add(n);
            }
            mtrx.add(mas);
        }
        return new Matrix(row, column, mtrx);
    }

    Matrix transposedMatrix() {
        var mtrx = new ArrayList<ArrayList<ComplexNumber>> ();
        for (int i = 0; i < column; i++) {
            var mas = new ArrayList<ComplexNumber>();
            for (int j = 0; j < row; j++) {
                mas.add(matrix.get(j).get(i));
            }
            mtrx.add(mas);
        }
        return new Matrix(column, row, mtrx);
    }


    ComplexNumber det() {
        if (column != row)
            throw new RuntimeException("Допустипо только с квадратной матрицей");
        return determinant(matrix, row);
    }

    private ComplexNumber determinant(ArrayList<ArrayList<ComplexNumber>> mrtx, int n) {
        ComplexNumber number = new ComplexNumber();
        if (n == 2) {
            return (mrtx.get(0).get(0).mul(mrtx.get(1).get(1))).
                    decr(mrtx.get(1).get(0).mul(mrtx.get(0).get(1)));
        }
        for (int i = 0; i < n; i++) {
            var m = new ArrayList<ArrayList<ComplexNumber>> ();
            for (int a = 1; a < n; a++) {
                var m_ = new ArrayList<ComplexNumber>();
                for (int b = 0; b < n; b++) {
                    if (b != i) {
                        m_.add(mrtx.get(a).get(b));
                    }
                }
                if (!m_.isEmpty()) m.add(m_);
            }
            number = number.add(((mrtx.get(0).get(i))
                    .mul(new ComplexNumber(Math.pow(-1, 2 + i)))).mul(determinant(m, m.size())) );
        }
        return number;
    }

}
