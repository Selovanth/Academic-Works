'''Alex Bernal'''
import os
import re

loopCheck = 'c'
filePath = ''

while loopCheck != 'Q'.upper():
    print("******************************")
    print("* Welcome to Tony's Pizzeria *")
    print("******************************")
    print("1. Read A Menu")
    print("2. Create A Menu")
    print("3. Delete A Menu")    
    print("4. Modify A Menu")    
    print("5. Create A Menu Folder")
    print("6. List Folder Contents")
    loopCheck=input('Make a selection: ').upper()
    if loopCheck == '1':
        searchPattern = '[a-zA-Z0-9" +"]+\.txt'
        filePath=input('\n\nEnter the path where your menu is: ')
        fileMatches = []
        for element in os.listdir(filePath):
            if re.match(searchPattern, element):
                fileMatches.append(element)
        index = 1
        for element in fileMatches:
            print(index, '. ', element, sep='')
            index += 1
        menuReadSelection=input("Enter the number of the menu you wish to read: ")
        fileLocation = filePath + "\\" + fileMatches[int(menuReadSelection)-1]
        fileToRead = open(fileLocation, 'r')
        print(fileToRead.read())
        fileToRead.close()
        continue
    elif loopCheck == '2':
        searchPattern = '[a-zA-Z0-9" +"]+\.txt'
        badName = True
        filePath=input('\n\nEnter the path where you want to create your menu: ')
        fileNameToCreate=input('Enter the name of desired menu: ')
        if re.match(searchPattern, fileNameToCreate):
            badName = False
        else:
            while badName == True:
                fileNameToCreate=input('Please end your menu name with a .txt extension: ')
                if re.match(searchPattern, fileNameToCreate):
                    badName = False
        fileToCreate=filePath + "\\" + fileNameToCreate
        newFile = open(fileToCreate, 'x')
        newFile.close()
        continue
    elif loopCheck == '3':
        searchPattern = '[a-zA-Z0-9" +"]+\.txt'
        filePath=input('\n\nEnter the path where your menu is: ')
        fileMatches = []
        for element in os.listdir(filePath):
            if re.match(searchPattern, element):
                fileMatches.append(element)
        index = 1
        for element in fileMatches:
            print(index, '. ', element, sep='')
            index += 1
        menuReadSelection=input("Enter the number of the menu you wish to delete: ")
        fileLocation = filePath + "\\" + fileMatches[int(menuReadSelection)-1]
        fileToDelete = os.remove(fileLocation)
        continue
    elif loopCheck == '4':
        moreContent = ''
        searchPattern = '[a-zA-Z0-9" +"]+\.txt'
        filePath=input('\n\nEnter the path where your menu is: ')
        fileMatches = []
        for element in os.listdir(filePath):
            if re.match(searchPattern, element):
                fileMatches.append(element)
        index = 1
        for element in fileMatches:
            print(index, '. ', element, sep='')
            index += 1
        menuReadSelection=input("Enter the number of the menu you wish to modify: ")
        fileLocation = filePath + "\\" + fileMatches[int(menuReadSelection)-1]
        fileToModify = open(fileLocation, 'a')
        fileToModify.write(input('Enter your new menu item: '))
        fileToModify.write('\n')
        moreContent=input('Would you like to add another item?\nY/N: ').upper()
        while moreContent == 'Y':
            fileToModify.write(input('Enter your new menu item: '))
            fileToModify.write('\n')
            moreContent=input('Would you like to add another item?\nY/N: ').upper()
        fileToModify.close()
        continue
    elif loopCheck == '5':
        moreFolders = ''
        folderPath=input('\n\nEnter the path where you want to create your folder: ')
        folderName=input('Enter the name of the folder you want to create: ')
        newFolder = folderPath + "\\" + folderName 
        os.mkdir(newFolder)
        for element in os.scandir(folderPath):
            if element.is_dir() and element.name==folderName:
                print("New directory: ", folderPath, "\\", folderName, sep='')
        moreFolders=input('Would you like to create another folder?\nY/N: ').upper()
        while moreFolders == 'Y':
            changePaths=input('Would you like to change paths?\nY/N: ').upper()
            if changePaths == 'N':
                folderName=input('Enter the name of the folder you want to create: ')
                newFolder = folderPath + "\\" + folderName 
                os.mkdir(newFolder)
                for element in os.scandir(folderPath):
                    if element.is_dir() and element.name==folderName:
                        print("New directory: ", folderPath, "\\", folderName, sep='')
                moreFolders=input('Would you like to create another folder?\nY/N: ').upper()
            else:
                folderPath=input('\n\nEnter the path where you want to create your folder: ')
                folderName=input('Enter the name of the folder you want to create: ')
                newFolder = folderPath + "\\" + folderName 
                os.mkdir(newFolder)
                for element in os.scandir(folderPath):
                    if element.is_dir() and element.name==folderName:
                        print("New directory: ", folderPath, "\\", folderName, sep='')
                moreFolders=input('Would you like to create another folder?\nY/N: ').upper()
        continue
    elif loopCheck == '6':
        
        folderPath=input('\n\nEnter the path of the folder you want to view: ')
        obj = os.scandir(folderPath)
        for element in obj:
            if element.is_dir or element.is_file:
                print(folderPath, '\\', element.name, sep='')
        continue

