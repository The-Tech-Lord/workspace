import stdarray


def any(array):
    for index in array:
        if not index:
            return True
        else:
            return False


def all(array):
    for index in array:
        if index:
            return False

    return True


def exactly(array, amount):
    false_val_counter = 0
    for index in array:
        if not index:
            false_val_counter += 1
        else:
            continue

    if false_val_counter == amount:
        return True
    else:
        return False


def count(array):
    false_val_counter = 0
    for index in array:
        if not index:
            false_val_counter += 1
        else:
            continue

    return false_val_counter


def _main():
    import stdio

    array = [False, False, True, False, True, True]
    stdio.writeln(any(array))
    stdio.writeln(all(array))
