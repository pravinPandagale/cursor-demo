#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ORDERS 100
#define MAX_NAME_LEN 50

struct Order {
    int id;
    char customer[MAX_NAME_LEN];
    float amount;
};

int main() {
    struct Order orders[MAX_ORDERS];
    int orderCount = 0;
    char filename[] = "orders.txt";
    FILE *fp = fopen(filename, "w");
    if (!fp) {
        printf("Error opening file for writing.\n");
        return 1;
    }

    int n;
    printf("Enter number of orders to save: ");
    scanf("%d", &n);
    if (n > MAX_ORDERS) n = MAX_ORDERS;

    for (int i = 0; i < n; i++) {
        printf("\nOrder #%d\n", i+1);
        printf("Enter Order ID: ");
        scanf("%d", &orders[i].id);
        printf("Enter Customer Name: ");
        scanf("%s", orders[i].customer);
        printf("Enter Amount: ");
        scanf("%f", &orders[i].amount);
        fprintf(fp, "%d,%s,%.2f\n", orders[i].id, orders[i].customer, orders[i].amount);
        orderCount++;
    }

    fclose(fp);
    printf("\n%d orders saved to %s\n", orderCount, filename);
    return 0;
}
