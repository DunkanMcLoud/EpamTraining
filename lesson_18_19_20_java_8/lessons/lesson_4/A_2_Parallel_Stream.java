package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_4;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class A_2_Parallel_Stream {

    private static class Book {
        private String content;
        private String rusContent;

        public Book(String content) {
            this.content = content;
        }

        public void translateToRussian() {
            System.out.println("[" + Thread.currentThread().getName() + "] " + "'Translate in action'");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rusContent = "Translated russian content";
        }

        public void translateToRussian(String company) {
            System.out.println("[" + Thread.currentThread().getName() + "] "
                                       + " " + company + " "
                                       + "'Translate in action'");
            if (company.compareToIgnoreCase("ivan") == 0) {
                try {
                    Thread.sleep(600000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rusContent = "Translated russian content";
        }
    }

    public static void main(String[] args) {

        int i = Runtime.getRuntime().availableProcessors();
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
        int j = Runtime.getRuntime().availableProcessors();

        System.out.println(i);
        List<Book> books = Arrays.asList(
                new Book("a_content"), new Book("b_content"),
                new Book("a_content"), new Book("b_content"),
                new Book("a_content"), new Book("b_content"),
                new Book("a_content"), new Book("b_content")
        );
        List<Book> books2 = Arrays.asList(
                new Book("a_content"), new Book("b_content"),
                new Book("a_content"), new Book("b_content"),
                new Book("a_content"), new Book("b_content"),
                new Book("a_content"), new Book("b_content")
        );

        /*long duration = calcDuration(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/

        long duration = 0;
       /* duration = calcDuration(() -> {
            translateBooksUsingForEach(books);
        });
*/
        // duration = calcDuration(() -> a_2_translateBooksUsingThreads(books));
        // duration = calcDuration(() -> a_3_translateBooksUsingStream(books));

        // duration = calcDuration(() -> a_4_translateBooksUsingParallelStream(books, "AAAA"));


     /*   duration = calcDuration(() -> {
            Thread threadIvan = modelUserTranslateBooks("Ivan", books);
            Thread threadPetr = modelUserTranslateBooks("Petr", books2);
            List<Thread> threads = Arrays.asList(threadIvan, threadPetr);

            threads.forEach(th -> {
                th.start();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
*/
        duration = calcDuration(() -> {
            Thread threadIvan = modelUserTranslateBooksWithPersonalPool("Ivan", books);
            Thread threadPetr = modelUserTranslateBooksWithPersonalPool("Petr", books2);
            List<Thread> threads = Arrays.asList(threadIvan, threadPetr);

            threads.forEach(th -> {
                th.start();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(duration);
    }


    private static List<Book> a_1_translateBooksUsingForEach(List<Book> books) {
        books.forEach(Book::translateToRussian);

        return books;
    }

    private static List<Book> a_2_translateBooksUsingThreads(List<Book> books) {

        List<Thread> threads = new ArrayList<>();

        books.forEach(book -> {
            threads.add(new Thread(book::translateToRussian));
        });

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return books;
    }

    private static List<Book> a_3_translateBooksUsingStream(List<Book> books) {
        return books
                .stream()
                .map(b -> {
                    b.translateToRussian();
                    return b;
                })
                .collect(Collectors.toList());
    }

    private static List<Book> a_4_translateBooksUsingParallelStream
            (List<Book> books, String company) {
        return books
                .stream()
                .parallel()
                .map(b -> {
                    b.translateToRussian(company);
                    return b;
                })
                .collect(Collectors.toList());
    }

    private static List<Book> a_4_translateBooksUsingParallelStreamWithPersonalPool
            (List<Book> books, String company) {

        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(() -> {
            books
                    .stream()
                    .parallel()
                    .map(b -> {
                        b.translateToRussian(company);
                        return b;
                    })
                    .collect(Collectors.toList());
        });

        return books;
    }


    private static Thread modelUserTranslateBooks(String name, List<Book> books) {
        return new Thread(() -> {
            a_4_translateBooksUsingParallelStream(books, name);
            //   if(name.contains("Ivan"))
            {
                try {
                    Thread.sleep(1000000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static Thread modelUserTranslateBooksWithPersonalPool(String name, List<Book> books) {
        return new Thread(() -> {
            a_4_translateBooksUsingParallelStreamWithPersonalPool(books, name);
            //   if(name.contains("Ivan"))
            {
                try {
                    Thread.sleep(1000000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static long calcDuration(DurationWrapper durationWrapper) {

        LocalDateTime begin = LocalDateTime.now();
        durationWrapper.runFunction();
        LocalDateTime end = LocalDateTime.now();

        Duration duration = Duration.between(begin, end);
        return duration.toMillis();
    }

    @FunctionalInterface
    interface DurationWrapper {
        void runFunction();
    }


}
