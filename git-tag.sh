#!/bin/bash

prefix="test-"
pre_prefix="pre-"

if `git status | grep "RELEASE" &>/dev/null`; then
    prefix="pro-"
fi
if [[ $1 == "ss" ]]; then
    prefix=${pre_prefix}
fi
function git-tag() {
    git push
    git pull --tags
    local new_tag=$(echo ${prefix}mi-$(date +'%Y%m%d')-$(git tag -l "${prefix}mi-$(date +'%Y%m%d')-*" | wc -l | xargs printf '%02d'))
    echo ${new_tag}
    git tag ${new_tag}
    git push origin $new_tag
}
git-tag;
