#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <errno.h>

int main(int argc, char *argv[]) {
    if (argc < 3) {
        fprintf(stderr, "Usage: %s <x> <y>\n", argv[0]);
        return 1;
    }

    errno = 0;
    unsigned long x = strtoul(argv[1], NULL, 10);
    unsigned long y = strtoul(argv[2], NULL, 10);

    if (errno != 0 || x > UINT_MAX || y > UINT_MAX) {
        fprintf(stderr, "Invalid input or value out of range.\n");
        return 1;
    }

    if (x > UINT_MAX - y) {
        fprintf(stderr, "Unsigned integer overflow detected.\n");
        return 1;
    }

    unsigned int sum = (unsigned int)(x + y);
    printf("Sum = %u\n", sum);
    return 0;
}
