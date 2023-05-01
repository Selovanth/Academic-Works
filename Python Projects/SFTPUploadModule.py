import paramiko, os, re
#AZURE SFTP Server 40.76.148.162
#HOME PC Server 47.135.147.55

###### VARIABLES ######
#Regex pattern to use for detection of valid IP address input
regexIPSearchPattern = ("\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}")
validIPEntered = False
validPortEntered = False
validUserIn = False
usingCert = False
notDone = True
host = ""
port = 0
keyFile = ""


#Prompt user for valid IP address for remote server
while validIPEntered == False:
    userInput = input("Enter the IP address and port you wish to connect to.\nIP Address: ")
    if not re.search(regexIPSearchPattern, userInput):
        print("\n\n**************************\n* \t\t  ERROR \t\t *\n**************************\nEnter a valid IP address")
    else:
        validIPEntered = True
        host = userInput

#Prompt user for valid port number for remote server
while validPortEntered == False:
    userInput = int(input("Port: "))
    if userInput >= 65535 or userInput < 0:
        print("\n\n**************************\n* \t\t  ERROR \t\t *\n**************************\nEnter a valid port number")
    else:
        validPortEntered = True
        port = userInput

#Create transport object with server IP and port
transport = paramiko.Transport((host, port))

#Check if user is connecting with a key file
while validUserIn == False:
    userInput = input("Are you using a key file to connect? Y/N: ").upper().strip()
    if userInput == "Y":
        validUserIn = True
        usingCert = True
    elif userInput == "N":
        validUserIn = True

#Prompt user for key file location if using a key file
if usingCert == True:
    validFile = False
    goodConnection = False
    while validFile == False:
        userInput = input("Specify the absolute path to your public key: ")
        if os.path.isfile(userInput):
            print("Key file accepted")
            keyFile = userInput
            validFile = True
        elif not os.path.isfile(userInput):
            print("\n**************************\n* \t\t  ERROR \t\t *\n**************************\nThe specified key file path is invalid. Please check your pathing to the key file")
    while goodConnection == False:
        username = input("\nEnter the username you wish to connect with\nUsername: ")
        keyFile = paramiko.RSAKey.from_private_key(open(keyFile))
        try:
            transport.connect(None, username, None, keyFile)
        except:
            print("\n\n**************************\n* \t\t  ERROR \t\t *\n**************************\nSomething went wrong homie")
        else:
            print("\nCredentials accepted. Connecting to the server...")
            goodConnection = True

#Prompt user for password if not using a keyfile
elif usingCert == False:
    goodCredentials = False
    while goodCredentials == False:
        username = input("\nEnter your credentials to connect to the server.\nUsername: ")
        password = input("Password: ")
        try:
            transport.connect(None, username, password)
        except:
            print("\n\n**************************\n* \t\t  ERROR \t\t *\n**************************\nInvalid credentials were entered")
        else:
            print("\nCredentials accepted. Connecting to the server...")
            goodCredentials = True

# Connect
sftp = paramiko.SFTPClient.from_transport(transport)

# SFTP upload function
def SFTP_Upload():
###### VARIABLES ######
    remotePath = "/"
    localPath = ""
    desiredUploadDirectoryNotFound = True
    desiredRemoteDirectoryNotFound = True
    uploadChoiceMade = False
    linuxServer = False
    currentDirectory = os.getcwd()
    localPath = currentDirectory
#Regex pattern to determine if user is attempt to traverse upward more than one directory in one movement
    multipleTraversalSearchPattern = ("\.\.\\\\\.\.")
    #sameDirectoryTraversal = (".\./.")
    sameDirectoryTraversal = ("[.\\a-zA-Z]")

#Determine if remote server is a Linux or Windows server
    remoteDirectoryQuery = sftp.listdir('/')
    if 'etc' in remoteDirectoryQuery:
        print("*************************\n* LINUX SERVER DETECTED *\n*************************")
        linuxServer = True
    else:
        print("********************************************************\n* LINUX SERVER NOT DETECTED... ASSUMING WINDOWS SERVER *\n********************************************************")

#Begin directory traversal by introducing user instructions
    print("*********************************************\n*\t  DIRECTORY TRAVERSAL INSTRUCTIONS \t    *\n*********************************************\n1. Enter .. to move up one directory"
          "\n2. Enter multiple .. separated by backslashes to move up more than one directory\n3. Enter an absolute path\n4. To view current directory enter PWD")

#Print current working directory and all subdirectories
    print(f"\n*********************************************\n* \t\t  CURRENT LOCAL DIRECTORY \t\t    *\n*********************************************\n{currentDirectory}\n")
    localDirectoryContents = os.listdir(currentDirectory)
    if linuxServer == False:
        entryNumber = 0
        for listing in localDirectoryContents:
            if os.path.isdir(currentDirectory + '\\' + listing):
                print(f"{entryNumber + 1}. {listing}")
                entryNumber+=1
    elif linuxServer == True:
        entryNumber = 0
        for listing in localDirectoryContents:
            if os.path.isdir(currentDirectory + '/' + listing):
                print(f"{entryNumber + 1}. {listing}")
                entryNumber+=1

#List remote FTP server contents correctly if Linux server determination was made earlier
    if linuxServer == True:
        try:
            if linuxServer == True:
                remoteDirectory = (remotePath + 'home/' + username)
            entryNumber = 0
            remoteDirectoryContents = sftp.listdir(remotePath + 'home/' + username)
            print(f"*********************************************\n*\t\t  CURRENT REMOTE DIRECTORY\t\t    *\n*********************************************\n{remoteDirectory}\n")
            for file in remoteDirectoryContents:
                print(f"{entryNumber + 1}. {file}")
                entryNumber+=1
        except:
            print("\nNah homie")
#List remote FTP server contents correctly if Windows server determination was made earlier
    elif linuxServer == False:
        try:
            entryNumber = 0
            remoteDirectoryContents = sftp.listdir(remotePath)
            print("*********************************************\n*\t\t  CURRENT REMOTE DIRECTORY\t\t    *\n*****************************************************\n")
            for file in remoteDirectoryContents:
                if file != 'desktop.ini':
                    print(f"{entryNumber + 1}. {file}")
                    entryNumber+=1
        except:
            print("\nNah homie")

#Begin directory traversal loop until user finds desired directory to upload from
    print("*********************************************\n*\t\t\t DIRECTORY TRAVERSAL\t\t\t*\n*****************************************************")
    while desiredUploadDirectoryNotFound == True:
        #If statement to continuously check on new loop iteration if currentDirectory is in list form or not to display current working directory neatly when printed
        if type(currentDirectory) is list:
            currentDirectory = '\\'.join(currentDirectory)
            print(f"*********************************************************\n*\t\t\t CURRENT LOCAL DIRECTORY\t\t*\n************************************************************\n{currentDirectory}")
        else:
            print(f"****************************************************\n*\t\t\t CURRENT LOCAL DIRECTORY\t\t*\n************************************************************\n{currentDirectory}")

        #Prompt user if the current working directory is their desired upload directory or not
        userInput = input("\nAre you in the current directory you want to upload from? Y/N: ").upper().strip()
        if userInput == "Y":
            localPath = currentDirectory
            desiredUploadDirectoryNotFound = False
        elif userInput == "N":
            userInput = input("Do you want to enter an absolute path? Y\\N: ").upper().strip()
            if userInput == "Y":
                userInput = input("Enter Path: ").strip()
                currentDirectory = userInput

        #If statement to continuously check on new loop iteration if currentDirectory is in list form or not to iterate directory contents correctly
        if type(currentDirectory) is not list:
            currentDirectory = currentDirectory.split('\\')


        #Receive user command


        #Move up one directory level
        if userInput == ".." and len(currentDirectory) > 1:
            currentDirectory.pop(-1)
            currentDirectory = '\\'.join(currentDirectory)
            print(f"*****************************************************\n*\t\t\t CURRENT LOCAL DIRECTORY\t\t\t    *\n*************************************************************\n{currentDirectory}")
            currentDirectory.split('\\')
        elif userInput == ".." and len(currentDirectory) == 1:
            print(f"\n\n**************************\n* \t\t  ERROR \t\t *\n**************************\nYou are attempting to traverse upward too many directories.\nThe maximum number of directories you can move up is {len(currentDirectory) - 1}")

        #Move up multiple directory levels
        elif re.search(multipleTraversalSearchPattern, userInput):
            traversalCount = int((userInput.count('.') / 2))
            if traversalCount > len(currentDirectory) - 1:
                print(f"\n\n**************************\n* \t\t  ERROR \t\t *\n**************************\nYou are attempting to traverse upward too many directories.\nThe maximum number of directories you can move up is {len(currentDirectory) - 1}")
            else:
                for amount in range (traversalCount):
                    currentDirectory.pop(-1)
                currentDirectory = '\\'.join(currentDirectory)
                print(f"*********************************************\n*\t\t\t CURRENT LOCAL DIRECTORY\t\t\t    *\n*****************************************************\n{currentDirectory}")
                currentDirectory = currentDirectory.split('\\')

        #Print current directory
        elif userInput == "PWD":
            if type(currentDirectory) is list:
                currentDirectory = '\\'.join(currentDirectory)
                print(f"*********************************************\n*\t\t\tCURRENT LOCAL DIRECTORY\t\t\t    *\n*****************************************************\n{currentDirectory}")
            else:
                print(f"*********************************************\n*\t\t\tCURRENT LOCAL DIRECTORY\t\t\t    *\n*****************************************************\n{currentDirectory}")


    #Begin loop to correctly set the remotePath variable
    while desiredRemoteDirectoryNotFound == True:
        userInput = input("Are you in the current directory you want to upload to? Y/N: ").upper().strip()
        if userInput == "Y":
            localPath = currentDirectory
            desiredRemoteDirectoryNotFound = False
        elif userInput == "N":
            print(f"\t\t\tCURRENT REMOTE WORKING DIRECTORY\n*********************************************\n{sftp.listdir('./')}")
            userInput = input("Enter the directory you would like to change into: ")
            remotePath = userInput
            desiredRemoteDirectoryNotFound = False
    print(f"*********************************************\n*\t\t  CURRENT REMOTE DIRECTORY\t\t    *\n*****************************************************\n{sftp.listdir(remotePath)}")

    #Begin loop to determine if user has selected a file to upload
    while uploadChoiceMade == False:
        currentDirectoryListing = os.listdir(localPath)
        print("*********************************************\n*\t\tCURRENT DIRECTORY LISTING\t\t    *\n*********************************************\n")
        entryNumber = 0

        #Lists current working directory contents
        for entry in currentDirectoryListing:
            print(f"{entryNumber + 1}. {entry}")
            entryNumber+=1

        userInput = ''
        #Prompt user for the item number they wish to upload
        userInput = int(input("Enter the number of the item you wish to upload: "))
        #Check if user input is valid for listed directory contents
        if userInput - 1 >= len(currentDirectory) or userInput - 1 < 0:
            print(f"\n\n**************************\n* \t\t  ERROR \t\t *\n**************************\nEnter a value between the range of 1 - {len(currentDirectory) - 1}")
            print(f"\n*********\n* DEBUG *\n*********\ncurrentDirectory type: {type(currentDirectory)}\nuserInput: {userInput}")
        #Check if user input is valid for listed directory contents
        elif type(userInput) is not int:
            print("\n\n**************************\n* \t\t  ERROR \t\t *\n**************************\nEnter a whole number")
        #User input is valid
        else:
            #localUploadPath is constructed using the current local path, backslashes are added, and the current directory item is selected using the number input by the user subtracted by 1 to correctly select the desired file
            #from the list object representing the CWD
            localUploadPath = (localPath + '\\' + currentDirectoryListing[userInput - 1])
            remotePath = (remotePath + currentDirectoryListing[userInput - 1])
            sftp.put(localUploadPath, remotePath)
            #If/Else statement to correctly "Reset" remotePath back to the desired upload directory, removing the designated upload file from the end of remotePath
            if '\\' in remotePath:
                print("DEBUG: IN IF STATEMENT")
                remotePath = remotePath.split('\\')
                print(f"DEBUG:\nREMOTE PATH SPLIT: {remotePath}")
                remotePath.pop(-1)
                print(f"DEBUG:\nREMOTE PATH SPLIT POP: {remotePath}")
                remotePath = ('\\'.join(remotePath) + '\\')
                print(f"DEBUG:\nREMOTE PATH JOIN: {remotePath}")
            elif '/' in remotePath:
                remotePath = remotePath.split("/")
                remotePath.pop(-1)
                remotePath = ('/'.join(remotePath) + '/')

        userInput = input("\nWould you like to upload another file? Y\\N: ").upper().strip()
        if userInput == "Y":
            continue
        elif userInput == "N":
            uploadChoiceMade = True

# SFTP download function
def SFTP_Download():
    DOWNLOAD_FOLDER = f"{os.getenv('USERPROFILE')}\\Downloads"
    remotePath = "/"
    entryNumber = 0
    SFTPEntries = []
    sftpClose = False

    while sftpClose == False:
        print("*********************************************\n*\t\t  REMOTE DIRECTORY CONTENTS\t\t\t*\n*********************************************")
        for entry in sftp.listdir(remotePath):
            if entry != "desktop.ini":
                SFTPEntries.append(entry)

        for entry in SFTPEntries:
            print(f"{entryNumber + 1}. {entry}")
            entryNumber += 1

        print("*************************************\n*\t\t\tDOWNLOAD FILE\t\t    *\n*************************************")

        userInput = int(input("Enter the number of the file you would like to download: "))
        if userInput > len(SFTPEntries) or userInput < 0:
            print(f"Enter a number within the range of 1 - {len(SFTPEntries)}")
        else:
            remotePath = f"/{SFTPEntries[userInput - 1]}"
            localPath = (DOWNLOAD_FOLDER + '\\' + SFTPEntries[userInput - 1])
            sftp.get(remotePath, localPath)

        userInput = input("Would you like to download another file? Enter Y/N: ").upper().strip()
        if userInput == "Y":
            continue
        elif userInput == "N":
            sftpClose = True
            sftp.close()
            transport.close()
        else:
            print("Invalid entry. Enter N for no or Y for yes.")

# Prompt user for desired FTP operation
userInput = input("Upload - U\nDownload - D\nSelection: ").upper().strip()

# Fork user input accordingly
if userInput == "U".upper().strip():
    SFTP_Upload()
elif userInput == "D".upper().strip():
    SFTP_Download()

while notDone == True:
    userInput = input("Would you like to upload or download more files? Y\\N: ").upper().strip()
    if userInput == "Y":
        userInput = input("Upload - U\nDownload - D\nSelection: ").upper().strip()
        if userInput == "U".upper().strip():
            SFTP_Upload()
        elif userInput == "D".upper().strip():
            SFTP_Download()
    elif userInput == "N":
        notDone = False
        sftp.close()
        transport.close()

# Final Connection Check to Close
if sftp: sftp.close()
if transport: transport.close()