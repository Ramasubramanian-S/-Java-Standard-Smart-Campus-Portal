
#!/bin/bash
# Get current project directory
PROJECT_DIR="$(pwd)"

# Backup destination
BACKUP_DIR="$HOME/remote-backups"

# Create backup folder if it doesn't exist
mkdir -p "$BACKUP_DIR"

# Generate timestamp for unique archive name
TIMESTAMP=$(date +"%Y%m%d_%H%M%S")
ARCHIVE_NAME="smartcampus_backup_$TIMESTAMP.tar.gz"

# Create backup archive
if [ -d "$PROJECT_DIR" ]; then
    tar -czf "$BACKUP_DIR/$ARCHIVE_NAME" -C "$PROJECT_DIR" .
    echo "Backup successful: $BACKUP_DIR/$ARCHIVE_NAME"
else
    echo " Project folder not found!"
fi


