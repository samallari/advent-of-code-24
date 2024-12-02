#include <stdio.h>
#include <stdlib.h>


// comparator function for qsort
int compare(const void *a, const void *b) {
    return (*(long *) a - *(long *) b);
}

int main() {
    FILE *file;
    char *filename = "input.txt";
    char line[256];

    // open file
    file = fopen(filename, "r");
    if (file == NULL) {
        fprintf(stderr, "Could not open file %s for reading\n", filename);
        return 1;
    }

    // get the number of lines in the file
    int line_count = 0;
    while (fgets(line, sizeof(line), file)) {
        line_count++;
    }
    rewind(file); // go back to beginning of file
    // debugging: print number of lines
    // printf("line count: %d\n", line_count);

    // instantiate arrays to store the values of the left and right lists
    int left[line_count];
    int right[line_count];

    // read the values from the file, store in arrays
    for (int i = 0; i < line_count; i++) {
        fscanf(file, "%d   %d", &left[i], &right[i]);
        // printf("left: %d, right: %d\n", left[i], right[i]);
    }

    // sort left and right lists from least -> greatest
    qsort(left, line_count, sizeof(int), compare);
    qsort(right, line_count, sizeof(int), compare);

    // debugging: print sorted lists
    // for(int i = 0; i < line_count; i++) {
    //     printf("left: %d, right: %d\n", left[i], right[i]);
    // }

    // part one:
    // calculate difference between left and right lists and add to sum
    int sum = 0;
    for (int i = 0; i < line_count; i++) {
        // printf("left: %d, right: %d, distance: %d\n", left[i], right[i], abs(left[i] - right[i]));
        sum += abs(left[i] - right[i]);
    }

    printf("difference sum: %d\n", sum);

    // part two:
    // calculate similarity score, ex. the left list contains a 3, which appears in the right list 3 times, thus its score is 3 * 3 = 9
    int scores[line_count]; // store the scores for each value in the left list

    int i = 0, j = 0;
    while (i < line_count && j < line_count) { // runs in O(n) time
        int count = 0;

        // count occurrences of left[i] in the right list
        while (j < line_count && right[j] < left[i]) { // skip over values in right list that are less than left[i]
            j++;
        }

        while (j < line_count && right[j] == left[i]) { // since the lists are sorted, values that are equal to left[i] will be adjacent to each other in right list
            count++;
            j++;
        }

        // calculate similarity score and store in scores array
        scores[i] = left[i] * count;
        i++;
    }

    // calculate the sum of the scores
    int score_sum = 0;
    for (int i = 0; i < line_count; i++) {
        score_sum += scores[i];
    }

    printf("score sum: %d\n", score_sum);

    fclose(file);
    return 0;
}
