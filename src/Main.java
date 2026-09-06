class LiveLockSimpleExample {
    private static boolean resourceInUse = false;
    private static volatile boolean thread1Active = true;
    private static volatile boolean thread2Active = true;

    void main() {

            Thread thread1 = new Thread(() -> {
                while (thread1Active) {
                    if (!resourceInUse) {
                        resourceInUse = true;
                        System.out.println("Поток 1: Захватил ресурс");

                        if (thread2Active) {
                            System.out.println("Поток 1: Уступаю ресурс потоку 2");
                            resourceInUse = false;
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Поток 1: Работаю с ресурсом");
                            break;
                        }
                    }
                }
                System.out.println("Поток 1: Завершен");
            });

            Thread thread2 = new Thread(() -> {
                while (thread2Active) {
                    if (!resourceInUse) {
                        resourceInUse = true;
                        System.out.println("Поток 2: Захватил ресурс");

                        if (thread1Active) {
                            System.out.println("Поток 2: Уступаю ресурс потоку 1");
                            resourceInUse = false;
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Поток 2: Работаю с ресурсом");
                            break;
                        }
                    }
                }
                System.out.println("Поток 2: Завершен");
            });

    thread1.start();
    thread2.start();

    try

            {
                Thread.sleep(2000);
            } catch(
            InterruptedException e)

            {
                e.printStackTrace();
            }

            thread1Active =false;
            thread2Active =false;

    System.out.println("Принудительное завершение");


    }
}





